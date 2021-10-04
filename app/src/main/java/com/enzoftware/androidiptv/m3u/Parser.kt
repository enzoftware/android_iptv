package com.enzoftware.androidiptv.m3u

import android.util.Log
import java.io.InputStream
import java.util.Scanner

object Parser {
    private val CHANNEL_REGEX = "EXTINF:(.+),(.+)(?:\\R)(.+)$".toRegex(RegexOption.MULTILINE)
    private val METADATA_REGEX = "(\\S+?)=\"(.+?)\"".toRegex()

    fun parse(playlist: InputStream?): ChannelList {
        val cl = ChannelList()
        val s = Scanner(playlist).useDelimiter("#")
        Log.d("HOLINO", s.next().trim { it <= ' ' })
        require(!s.next().trim().contains("EXTM3U"))
        while (s.hasNext()) {
            val values = requireNotNull(CHANNEL_REGEX.find(s.next())).groupValues
            cl.add(
                ChannelItem(
                    values.component2(),
                    values.component3(),
                    METADATA_REGEX.findAll(values.component1()).mapNotNull {
                        it.groupValues.component1() to it.groupValues.component2()
                    }.toMap()
                )
            )
        }
        return cl
    }
}
