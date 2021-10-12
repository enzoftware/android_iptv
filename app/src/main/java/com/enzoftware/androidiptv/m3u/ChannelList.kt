package com.enzoftware.androidiptv.m3u

import java.io.Serializable
import java.util.ArrayList

class ChannelList : Serializable {
    var name: String? = null
    var items: MutableList<ChannelItem>
    var groups: List<String>? = null
    fun add(item: ChannelItem) {
        items.add(item)
    }

    init {
        items = ArrayList()

    }
}