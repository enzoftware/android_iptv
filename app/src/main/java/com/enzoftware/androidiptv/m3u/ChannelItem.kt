package com.enzoftware.androidiptv.m3u

import com.devbrackets.android.playlistcore.api.PlaylistItem
import com.devbrackets.android.playlistcore.manager.BasePlaylistManager
import java.io.Serializable

class ChannelItem(
    val name: String?,
    val url: String?,
    val metadata: Map<String, String>
) : PlaylistItem, Serializable {
    var duration = 0

    override val album: String = "album"
    override val artist: String = "artist"
    override val artworkUrl: String = "url"
    override val downloaded: Boolean = false
    override val downloadedMediaUri: String = ""
    override val id: Long = 0

    override val mediaType: Int = BasePlaylistManager.VIDEO

    override val mediaUrl: String?
        get() = url

    override val thumbnailUrl: String = ""

    override val title: String?
        get() = name
}
