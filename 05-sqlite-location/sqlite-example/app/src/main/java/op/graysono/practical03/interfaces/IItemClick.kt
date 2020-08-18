package op.graysono.practical03.interfaces

import android.widget.ImageButton
import op.graysono.practical03.helpers.Playlist

interface IItemClick {
    fun onItemClick(playlist: Playlist, imgBtn: ImageButton)
}



