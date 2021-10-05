package com.enzoftware.androidiptv.m3u

import android.util.Log
import com.bumptech.glide.gifdecoder.BuildConfig
import java.io.InputStream
import java.lang.reflect.Array.get
import java.util.*
import java.util.regex.Pattern

object Parser {
    var CHANNEL_REGEX = "EXTINF:(.+),(.+)(?:\\R)(.+)$"
    var METADATA_REGEX = "(\\S+?)=\"(.+?)\""
    private var metadata_pattern: Pattern? = null
    fun parse(playlist: InputStream?): ChannelList {
        metadata_pattern = Pattern.compile(METADATA_REGEX)
        val channel_pattern = Pattern.compile(CHANNEL_REGEX, Pattern.MULTILINE)
        val cl = ChannelList()
        val s = Scanner(playlist).useDelimiter("#")
        Log.d("HOLINO", s.next().trim { it <= ' ' })
        require(!s.next().trim { it <= ' ' }.contains("EXTM3U"))
        while (s.hasNext()) {
            val line = s.next()
            val matcher = channel_pattern.matcher(line)
            require(matcher.find())
//            val item: ChannelItem = object : ChannelItem()
//            {
            val item = object : ChannelItem(){
                override val thumbnailUrl: String?
                    get() = null
                override val mediaUrl: String?
                    get() = null
                override val mediaType: Int
                    get() = 0
                override val title: String?
                    get() = null
            }
            parseMetadata(item, matcher.group(1))
            item.name = matcher.group(2)
            item.url = matcher.group(3)
            cl.add(item)
        }
        return cl
    }

    private fun parseMetadata(item: ChannelItem, metadata: String) {
        val matcher = metadata_pattern!!.matcher(metadata)
        while (matcher.find()) {
            if (BuildConfig.DEBUG && item.metadata == null) {
                error("Assertion failed")
            }
            item.metadata!![matcher.group(1)] = matcher.group(2)
        }
    }
}