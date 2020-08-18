package op.graysono.practical03.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import op.graysono.practical03.R
import op.graysono.practical03.interfaces.IItemClick

class PlaylistRecyclerViewAdapter(
    var listener: IItemClick,
    private var playlists: ArrayList<Playlist>
) :
    RecyclerView.Adapter<PlaylistViewHolder>() {

    override fun getItemCount(): Int {
        return if (playlists.isNotEmpty()) playlists.size else 0
    }

    fun notifyData(newPlaylists: ArrayList<Playlist>) {
        playlists = newPlaylists
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.playlist_list_item, parent, false)
        return PlaylistViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: PlaylistViewHolder, position: Int) {
        val playlist: Playlist = playlists[position]
        viewHolder.txvName.text =
            viewHolder.itemView.context.getString(R.string.playlist_name, playlist.name)
        viewHolder.txvDateTime.text = viewHolder.itemView.context.getString(
            R.string.playlist_date,
            DateTime.formatDateTime(playlist.dateTime!!)
        )
        viewHolder.imgBtnMenu.setOnClickListener(
            MenuOnButtonClickListener(
                playlist,
                viewHolder.imgBtnMenu
            )
        )
    }

    inner class MenuOnButtonClickListener(var playlist: Playlist, var imgBtn: ImageButton) :
        View.OnClickListener {
        override fun onClick(v: View) {
            listener.onItemClick(playlist, imgBtn)
        }
    }
}



