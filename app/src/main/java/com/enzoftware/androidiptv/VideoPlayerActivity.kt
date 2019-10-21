package com.enzoftware.androidiptv

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devbrackets.android.exomedia.listener.OnPreparedListener
import com.devbrackets.android.exomedia.ui.widget.VideoView


class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        this.videoView = findViewById(R.id.videoView)
        setupVideoView()


    }

    private fun setupVideoView() {
        // Make sure to use the correct VideoView import
        videoView.setOnPreparedListener(dd())

        //For now we just picked an arbitrary item to play
        videoView.setVideoURI(Uri.parse("https://archive.org/download/Popeye_forPresident/Popeye_forPresident_512kb.mp4"))
    }

    fun dd(): OnPreparedListener {

    }

}
