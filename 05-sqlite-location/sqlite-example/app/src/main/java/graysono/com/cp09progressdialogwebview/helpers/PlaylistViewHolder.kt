package graysono.com.cp09progressdialogwebview.helpers

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import graysono.com.cp09progressdialogwebview.R

class PlaylistViewHolder(view: View) : RecyclerView.ViewHolder(view)  {
    var txvName: TextView = view.findViewById(R.id.txvPlaylistName)
    var txvDateTime: TextView = view.findViewById(R.id.txvPlaylistDateTime)
    var imgBtnMenu: ImageButton = view.findViewById(R.id.imgBtnPlaylistMenu)
}



