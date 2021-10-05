package com.enzoftware.androidiptv.m3u

import com.enzoftware.androidiptv.m3u.ChannelItem
import java.io.Serializable
import java.util.ArrayList

class ChannelList : Serializable {
    var name: String? = null
    @JvmField
    var items: MutableList<ChannelItem> = ArrayList()
    var groups: List<String>? = null
    fun add(item: ChannelItem) {
        items.add(item)
    }

}