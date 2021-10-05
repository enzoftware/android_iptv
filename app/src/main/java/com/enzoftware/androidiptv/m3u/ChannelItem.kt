package com.enzoftware.androidiptv.m3u

import com.devbrackets.android.playlistcore.api.PlaylistItem
import com.devbrackets.android.playlistcore.manager.BasePlaylistManager
import java.io.Serializable
import java.util.*


// making the ChannelItem class abstract after converting the code to Kotlin
abstract class ChannelItem : PlaylistItem, Serializable {
    var duration = 0
    @JvmField
    var name: String? = null
    @JvmField
    var url: String? = null
    @JvmField
    var metadata: HashMap<String, String>? = null

    //    public ChannelItem() {
    //        metadata = new HashMap<String, String>();
    //    }
    override val album: String?
        get() = "album"
    override val artist: String?
        get() = "artist"
    override val artworkUrl: String?
        get() = "url"
    override val downloaded: Boolean
        get() = false
    override val downloadedMediaUri: String?
        get() = ""
    override val id: Long
        get() = 0

}