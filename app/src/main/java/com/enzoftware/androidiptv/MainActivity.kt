package com.enzoftware.androidiptv

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.enzoftware.androidiptv.adapter.ChannelListAdapter
import com.enzoftware.androidiptv.m3u.Parser
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Output
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.FileNotFoundException
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private var kryo: Kryo? = null
    private var cla: ChannelListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("http://iptv.liber.pe:4443/playlist.m3u8?auth=eric:optical")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(
                        this@MainActivity, "Error fetching URL - " + e.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val cl = Parser.parse(response.body()!!.byteStream())
                    runOnUiThread {
                        cla = ChannelListAdapter(this@MainActivity, cl)
                        channel_list.adapter = cla
                        try {
                            val output = Output(openFileOutput("playlist.bin", MODE_PRIVATE))
                            kryo?.writeObject(output, cl)
                            output.close()
                        } catch (e: FileNotFoundException) {
                        }
                    }
                } catch (e: IllegalArgumentException) {
                    runOnUiThread {
                        Toast.makeText(
                            this@MainActivity, "Error parsing M3U",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        })

        search.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if (cla != null && !cla!!.isEmpty)
                    cla!!.filter.filter(s)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {}
        })

    }
}
