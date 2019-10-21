package com.enzoftware.androidiptv.playlist

import android.net.Uri
import android.util.Log
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import com.devbrackets.android.exomedia.ui.widget.VideoView
import com.devbrackets.android.playlistcore.data.PlaybackState
import com.devbrackets.android.playlistcore.listener.PlaylistListener
import com.devbrackets.android.playlistcore.manager.BasePlaylistManager
import com.enzoftware.androidiptv.m3u.ChannelItem

class VideoApi(var videoView: VideoView) : BaseMediaApi(), PlaylistListener<ChannelItem> {

    override val isPlaying: Boolean
        get() = videoView.isPlaying

    override val handlesOwnAudioFocus: Boolean
        get() = false

    override val currentPosition: Long
        get() = if (prepared) videoView.currentPosition else 0

    override val duration: Long
        get() = if (prepared) videoView.duration else 0

    override val bufferedPercent: Int
        get() = bufferPercent

    init {
        Log.d("HOLA", "HOLA1")
        videoView.setOnErrorListener(this)
        videoView.setOnPreparedListener(this)
        videoView.setOnCompletionListener(this)
        videoView.setOnSeekCompletionListener(this)
        videoView.setOnBufferUpdateListener(this)
    }

    override fun play() {
        Log.d("HOLA", "HOLA2")
        videoView.start()
    }

    override fun pause() {
        videoView.pause()
    }

    override fun stop() {
        videoView.stopPlayback()
    }

    override fun reset() {
        // Purposefully left blank
    }

    override fun release() {
        videoView.suspend()
    }

    override fun setVolume(
        @FloatRange(from = 0.0, to = 1.0) left: Float, @FloatRange(
            from = 0.0,
            to = 1.0
        ) right: Float
    ) {
        videoView.volume = (left + right) / 2
    }

    override fun seekTo(@IntRange(from = 0) milliseconds: Long) {
        videoView.seekTo(milliseconds.toInt().toLong())
    }

    override fun handlesItem(item: ChannelItem): Boolean {
        return item.mediaType == BasePlaylistManager.VIDEO
    }

    override fun playItem(item: ChannelItem) {
        Log.d("HOLA", "HOLA")
        prepared = false
        bufferPercent = 0
        videoView.setVideoURI(Uri.parse(item.mediaUrl))
    }

    /*
     * PlaylistListener methods used for keeping the VideoControls provided
     * by the ExoMedia VideoView up-to-date with the current playback state
     */
    override fun onPlaylistItemChanged(
        currentItem: ChannelItem?,
        hasNext: Boolean,
        hasPrevious: Boolean
    ): Boolean {
        videoView.videoControls?.let { controls ->
            // Updates the VideoControls display text
            controls.setTitle(currentItem?.title ?: "")
            controls.setSubTitle(currentItem?.album ?: "")
            controls.setDescription(currentItem?.artist ?: "")

            // Updates the VideoControls button visibilities
            controls.setPreviousButtonEnabled(hasPrevious)
            controls.setNextButtonEnabled(hasNext)
        }

        return false
    }

    override fun onPlaybackStateChanged(playbackState: PlaybackState): Boolean {
        return false
    }
}
