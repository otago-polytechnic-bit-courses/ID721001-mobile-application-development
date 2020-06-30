package op.graysono.practical03.activities

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import op.graysono.practical03.R
import op.graysono.practical03.enums.DownloadStatus
import op.graysono.practical03.enums.DownloadStatus.OK
import op.graysono.practical03.helpers.*
import op.graysono.practical03.interfaces.IDataDownloadAvailable
import op.graysono.practical03.interfaces.IDataDownloadComplete
import op.graysono.practical03.interfaces.IRecyclerViewItem

class MainActivity : AppCompatActivity(), IDataDownloadAvailable,
    IDataDownloadComplete, IRecyclerViewItem {

    private lateinit var imageRecyclerView: RecyclerView
    private lateinit var rawDataAsyncTask: RawDataAsyncTask
    private lateinit var lastFmRecyclerViewAdapter: LastFmRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lastFmRecyclerViewAdapter = LastFmRecyclerViewAdapter(ArrayList())
        imageRecyclerView = findViewById(R.id.album_recycler)
        imageRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        imageRecyclerView.addOnItemTouchListener(RecyclerItemClickListener(
            this@MainActivity,
            imageRecyclerView,
            this
        ))
        imageRecyclerView.adapter = lastFmRecyclerViewAdapter

        val url: String = buildUri(
            getString(R.string.base_url), getString(R.string.method),
            "cher", getString(R.string.api_key), getString(R.string.format)
        )
        rawDataAsyncTask = RawDataAsyncTask(this)
        rawDataAsyncTask.execute(url)
    }

    override fun onResume() {
        super.onResume()
        val sharedPref: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
        val queryResult: String? = sharedPref.getString("album_query", "")

        if (queryResult!!.isNotEmpty()) {
            val url: String = buildUri(
                getString(R.string.base_url), getString(R.string.method),
                queryResult, getString(R.string.api_key), getString(R.string.format)
            )
            rawDataAsyncTask = RawDataAsyncTask(this)
            rawDataAsyncTask.execute(url)
        }
    }

    private fun buildUri(
        baseURL: String, method: String, artist: String,
        apiKey: String, format: String
    ): String {
        return Uri.parse(baseURL)
            .buildUpon()
            .appendQueryParameter("method", method)
            .appendQueryParameter("artist", artist)
            .appendQueryParameter("api_key", apiKey)
            .appendQueryParameter("format", format)
            .build().toString()
    }

    override fun onDataAvailable(data: ArrayList<Album>) {
        Log.d(getString(R.string.TAG), getString(R.string.on_data_available, data))
        lastFmRecyclerViewAdapter.loadNewData(data)
    }

    override fun onError(e: Exception) {
        Log.d(getString(R.string.TAG), getString(R.string.on_error, e.message))
    }

    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        if (status == OK) {
            val lastFmAsyncTask = LastFmAsyncTask(this)
            lastFmAsyncTask.execute(data)
        }
    }

    override fun onItemClick(view: View, position: Int) {
        val album: Album? = lastFmRecyclerViewAdapter.getAlbum(position)
        if (album != null) {
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("album", album)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_main -> {
                startActivity(Intent(this@MainActivity, SearchActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}