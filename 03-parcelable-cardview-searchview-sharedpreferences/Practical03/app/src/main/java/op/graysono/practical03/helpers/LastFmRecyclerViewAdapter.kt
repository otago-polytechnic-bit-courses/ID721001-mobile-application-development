package op.graysono.practical03.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import op.graysono.practical03.R

class LastFmRecyclerViewAdapter(private var albums: ArrayList<Album>) :
    RecyclerView.Adapter<LastFmViewHolder>() {

    fun loadNewData(newAlbums: ArrayList<Album>) {
        albums = newAlbums
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (albums.isNotEmpty()) albums.size else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastFmViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        return LastFmViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: LastFmViewHolder, position: Int) {
        val album: Album = albums[position]

        viewHolder.albumNameText.text =
            viewHolder.itemView.context.getString(R.string.album_name, album.name)

        Picasso.with(viewHolder.albumImageView.context)
            .load(album.image)
            .placeholder(R.drawable.ic_baseline_album_black_24)
            .error(R.drawable.ic_baseline_album_black_24)
            .into(viewHolder.albumImageView)

        viewHolder.albumPlayCountText.text =
            viewHolder.itemView.context.getString(R.string.play_count, album.playCount.toString())
    }
}