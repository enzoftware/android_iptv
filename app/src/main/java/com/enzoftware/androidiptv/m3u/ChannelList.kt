package com.enzoftware.androidiptv.m3u

import java.io.Serializable

class ChannelList : Serializable {
    var name: String? = null
    val items: MutableList<ChannelItem> = mutableListOf()
    var groups: MutableList<String>? = null

    fun add(item: ChannelItem) {
        items.add(item)
    }
}