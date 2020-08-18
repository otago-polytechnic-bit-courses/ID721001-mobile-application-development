package op.graysono.practical03.activities

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import op.graysono.practical03.helpers.Playlist
import op.graysono.practical03.R
import op.graysono.practical03.enums.DatabaseStatus
import op.graysono.practical03.helpers.AlertDialog
import op.graysono.practical03.helpers.DBHelper
import op.graysono.practical03.helpers.PlaylistRecyclerViewAdapter
import op.graysono.practical03.interfaces.IItemClick

class PlaylistActivity : AppCompatActivity(), IItemClick {
    private lateinit var playlists: ArrayList<Playlist>
    private lateinit var dbHelper: DBHelper
    private lateinit var addBtn: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var playlistRecyclerViewAdapter: PlaylistRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

        playlists = ArrayList()
        dbHelper = DBHelper(this@PlaylistActivity)
        playlists = dbHelper.selectAll()

        addBtn = findViewById(R.id.add_playlist_btn)
        addBtn.setOnClickListener {
            addNewPlaylistDialog(DatabaseStatus.INSERT, 0, "")
        }

        recyclerView = findViewById(R.id.playlist_recycler_view)
        val layoutManager = LinearLayoutManager(this@PlaylistActivity)
        recyclerView.layoutManager = layoutManager
        playlistRecyclerViewAdapter = PlaylistRecyclerViewAdapter(this, playlists)
        recyclerView.adapter = playlistRecyclerViewAdapter

        readDatabase()
    }

    private fun readDatabase() {
        playlists = dbHelper.selectAll()
        playlistRecyclerViewAdapter.notifyData(playlists)
    }

    private fun addNewPlaylistDialog(status: DatabaseStatus, id: Int, txt: String) {
        val dialog = Dialog(this@PlaylistActivity, R.style.AppTheme_DialogFullScreen)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(R.layout.fragment_add_playlist)

        val addPlaylistEditText: EditText = dialog.findViewById(R.id.add_playlist_edit_text)
        val closePlaylistBtn: Button = dialog.findViewById(R.id.close_playlist_btn)
        val addPlaylistBtn: Button = dialog.findViewById(R.id.add_playlist_btn)
        val addPlaylistText: TextView = dialog.findViewById(R.id.add_playlist_text)

        if (status == DatabaseStatus.UPDATE) {
            addPlaylistEditText.setText(txt)
            addPlaylistBtn.text = getString(R.string.update)
            addPlaylistText.text = getString(R.string.update_playlist)
        } else {
            addPlaylistEditText.setText("")
            addPlaylistBtn.text = getString(R.string.add)
            addPlaylistText.text = getString(R.string.add_playlist)
        }

        addPlaylistBtn.setOnClickListener {
            if (status == DatabaseStatus.UPDATE) {
                dbHelper.update(id.toLong(), addPlaylistEditText.text.toString().trim())
                readDatabase()
            } else if (status == DatabaseStatus.INSERT) {
                dbHelper.insert(addPlaylistEditText.text.toString().trim())
                readDatabase()
            }
            dialog.dismiss()
        }

        closePlaylistBtn.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }

    override fun onItemClick(playlist: Playlist, imgBtn: ImageButton) {
        val popup = PopupMenu(this, imgBtn)
        popup.menuInflater.inflate(R.menu.menu_playlist, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.update -> addNewPlaylistDialog(
                    DatabaseStatus.UPDATE,
                    playlist.id,
                    playlist.name!!
                )
                R.id.delete -> {
                    val deletePlaylistAlertDialog = AlertDialog(
                        this@PlaylistActivity,
                        R.layout.delete_playlist_item
                    )
                    deletePlaylistAlertDialog.show(
                        R.string.delete_item, dbHelper, playlist, playlistRecyclerViewAdapter
                    )
                    readDatabase()
                }
            }
            true
        }
        popup.show()
    }
}
