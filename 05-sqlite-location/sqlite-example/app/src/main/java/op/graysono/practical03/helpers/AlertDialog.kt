package op.graysono.practical03.helpers

import android.app.AlertDialog
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import op.graysono.practical03.R

class AlertDialog(private val context: Context, layout: Int) {
    private lateinit var alertDialog: AlertDialog
    private val inflater: LayoutInflater =
        context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val view: View = inflater.inflate(layout, null)
    private val builder = AlertDialog.Builder(context)

    fun show(
        title: Int,
        dbHelper: DBHelper,
        playlist: Playlist,
        playlistRecyclerViewAdapter: PlaylistRecyclerViewAdapter
    ) {
        builder.setTitle(title)
        alertDialog = builder.setView(view).create()
        builder.setPositiveButton(context.getString(R.string.builder_positive)) { _, _ ->
            dbHelper.delete(playlist.id.toLong())
            playlistRecyclerViewAdapter.notifyData(dbHelper.selectAll())
        }
        builder.setNegativeButton(context.getString(R.string.builder_negative)) { _, _ -> dismiss() }
        alertDialog.setCanceledOnTouchOutside(true)
        builder.show()
    }

    private fun dismiss() {
        if (alertDialog.isShowing) alertDialog.dismiss()
    }
}
