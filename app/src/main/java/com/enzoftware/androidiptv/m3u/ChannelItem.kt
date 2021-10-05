package com.enzoftware.androidiptv.m3u;

import com.devbrackets.android.playlistcore.api.PlaylistItem;
import com.devbrackets.android.playlistcore.manager.BasePlaylistManager;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.HashMap;


public class ChannelItem implements PlaylistItem, Serializable {
    public int duration;
    public String name, url;
    public HashMap<String, String> metadata;

//    public ChannelItem() {
//        metadata = new HashMap<String, String>();
//    }

    @Nullable
    @Override
    public String getAlbum() {
        return "album";
    }

    @Nullable
    @Override
    public String getArtist() {
        return "artist";
    }

    @Nullable
    @Override
    public String getArtworkUrl() {
        return "url";
    }

    @Override
    public boolean getDownloaded() {
        return false;
    }

    @Nullable
    @Override
    public String getDownloadedMediaUri() {
        return "";
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public int getMediaType() {
        return BasePlaylistManager.VIDEO;
    }

    @Nullable
    @Override
    public String getMediaUrl() {
        return url;
    }

    @Nullable
    @Override
    public String getThumbnailUrl() {
        return "";
    }

    @Nullable
    @Override
    public String getTitle() {
        return name;
    }
}
