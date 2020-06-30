package op.graysono.practical03.helpers

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import op.graysono.practical03.R

class LastFmViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var albumNameText: TextView = view.findViewById(R.id.album_name_text)
    var albumImageView: CircleImageView = view.findViewById(R.id.album_image_view)
    var albumPlayCountText: TextView = view.findViewById(R.id.album_play_count_text)
}