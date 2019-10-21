package com.enzoftware.androidiptv.playlist

import com.devbrackets.android.exomedia.listener.*
import com.devbrackets.android.playlistcore.api.MediaPlayerApi
import com.devbrackets.android.playlistcore.listener.MediaStatusListener
import com.enzoftware.androidiptv.m3u.ChannelItem

abstract class BaseMediaApi : MediaPlayerApi<ChannelItem>, OnPreparedListener, OnCompletionListener,
    OnErrorListener, OnSeekCompletionListener, OnBufferUpdateListener {
    protected var prepared: Boolean = false
    protected var bufferPercent: Int = 0

    protected var statusListener: MediaStatusListener<ChannelItem>? = null

    override fun setMediaStatusListener(listener: MediaStatusListener<ChannelItem>) {
        statusListener = listener
    }

    override fun onCompletion() {
        statusListener?.onCompletion(this)
    }

    override fun onError(e: Exception): Boolean {
        return statusListener?.onError(this) == true
    }

    override fun onPrepared() {
        prepared = true
        statusListener?.onPrepared(this)
    }

    override fun onSeekComplete() {
        statusListener?.onSeekComplete(this)
    }

    override fun onBufferingUpdate(percent: Int) {
        bufferPercent = percent
        statusListener?.onBufferingUpdate(this, percent)
    }
}
