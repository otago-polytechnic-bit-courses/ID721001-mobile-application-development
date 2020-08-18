package op.graysono.practical03.helpers

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import op.graysono.practical03.R

class PlaylistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var txvName: TextView = view.findViewById(R.id.playlist_name_text)
    var txvDateTime: TextView = view.findViewById(R.id.playlist_date_time_text)
    var imgBtnMenu: ImageButton = view.findViewById(R.id.playlist_menu_img_btn)
}



