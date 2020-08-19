package graysono.com.cp09progressdialogwebview.activities

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
import graysono.com.cp09progressdialogwebview.R
import graysono.com.cp09progressdialogwebview.custom.CustomAlertDialog
import graysono.com.cp09progressdialogwebview.enums.DatabaseStatus
import graysono.com.cp09progressdialogwebview.helpers.DBHelper
import graysono.com.cp09progressdialogwebview.helpers.Playlist
import graysono.com.cp09progressdialogwebview.helpers.PlaylistRecyclerViewAdapter
import graysono.com.cp09progressdialogwebview.interfaces.IItemClick

class PlaylistActivity : AppCompatActivity(), IItemClick {
    private lateinit var playlists: ArrayList<Playlist>
    private lateinit var dbHelper: DBHelper
    private lateinit var btnAdd: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var playlistRecyclerViewAdapter: PlaylistRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

        playlists = ArrayList()
        dbHelper = DBHelper(this@PlaylistActivity)
        playlists = dbHelper.selectAll()

        btnAdd = findViewById(R.id.btnAddPlaylist)
        btnAdd.setOnClickListener {
            addNewPlaylistDialog(DatabaseStatus.INSERT, 0, "")
        }

        recyclerView = findViewById(R.id.rcvPlaylists)
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
        val dialog = Dialog(this@PlaylistActivity, R.style.DialogFullScreen)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(R.layout.fragment_add_playlist)

        val edtAddPlaylist: EditText = dialog.findViewById(R.id.edtAddPlaylist)
        val btnClosePlaylist: Button = dialog.findViewById(R.id.btnClosePlaylist)
        val btnAddPlaylist: Button = dialog.findViewById(R.id.btnAddPlaylist)
        val txvAddPlaylist: TextView = dialog.findViewById(R.id.txvAddPlaylist)

        if (status == DatabaseStatus.UPDATE) {
            edtAddPlaylist.setText(txt)
            btnAddPlaylist.text = "Update"
            txvAddPlaylist.text = "Update Playlist"
        } else {
            edtAddPlaylist.setText("")
            btnAddPlaylist.text = "Add"
            txvAddPlaylist.text = "Add New Playlist"
        }

        btnAddPlaylist.setOnClickListener {
            if (status == DatabaseStatus.UPDATE) {
                dbHelper.update(id.toLong(), edtAddPlaylist.text.toString().trim())
                readDatabase()
            } else if (status == DatabaseStatus.INSERT) {
                dbHelper.insert(edtAddPlaylist.text.toString().trim())
                readDatabase()
            }
            dialog.dismiss()
        }

        btnClosePlaylist.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }

    override fun onItemClick(playlist: Playlist, imgBtn: ImageButton) {
        val popup = PopupMenu(this, imgBtn)
        popup.menuInflater.inflate(R.menu.menu_playlist, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.update -> addNewPlaylistDialog(DatabaseStatus.UPDATE, playlist.id, playlist.name!!)
                R.id.delete -> {
                    val deletePlaylistAlertDialog = CustomAlertDialog(this@PlaylistActivity, R.layout.custom_delete_playlist_item)
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
