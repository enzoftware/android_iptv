package com.enzoftware.androidiptv

import android.app.Application
import com.enzoftware.androidiptv.manager.PlaylistManager


/**
 * Created by Enzo Lizama Paredes on 2019-10-21.
 * Contact: lizama.enzo@gmail.com
 */
class App : Application() {
    val playlistManager: PlaylistManager by lazy { PlaylistManager(this) }
}