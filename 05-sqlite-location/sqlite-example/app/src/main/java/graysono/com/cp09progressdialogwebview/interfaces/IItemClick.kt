package graysono.com.cp09progressdialogwebview.interfaces

import android.widget.ImageButton
import graysono.com.cp09progressdialogwebview.helpers.Playlist

interface IItemClick {
    fun onItemClick(playlist: Playlist, imgBtn: ImageButton)
}



