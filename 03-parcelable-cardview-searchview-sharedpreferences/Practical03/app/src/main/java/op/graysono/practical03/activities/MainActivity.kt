package op.graysono.practical03.activities

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import op.graysono.practical03.R
import op.graysono.practical03.enums.DownloadStatus
import op.graysono.practical03.enums.DownloadStatus.OK
import op.graysono.practical03.helpers.Album
import op.graysono.practical03.helpers.LastFmAsyncTask
import op.graysono.practical03.helpers.LastFmRecyclerViewAdapter
import op.graysono.practical03.helpers.RawDataAsyncTask
import op.graysono.practical03.interfaces.IDataDownloadAvailable
import op.graysono.practical03.interfaces.IDataDownloadComplete

class MainActivity : AppCompatActivity(), IDataDownloadAvailable,
    IDataDownloadComplete {

    private lateinit var imageRecyclerView: RecyclerView
    private lateinit var rawDataAsyncTask: RawDataAsyncTask
    private lateinit var lastFmRecyclerViewAdapter: LastFmRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lastFmRecyclerViewAdapter = LastFmRecyclerViewAdapter(ArrayList())
        imageRecyclerView = findViewById(R.id.album_recycler)
        imageRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        imageRecyclerView.adapter = lastFmRecyclerViewAdapter

        val url: String = buildUri(
            getString(R.string.base_url), getString(R.string.method),
            "cher", getString(R.string.api_key), getString(R.string.format)
        )
        rawDataAsyncTask = RawDataAsyncTask(this)
        rawDataAsyncTask.execute(url)
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

    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        if (status == OK) {
            val lastFmAsyncTask = LastFmAsyncTask(this)
            lastFmAsyncTask.execute(data)
        }
    }

    override fun onDataAvailable(data: ArrayList<Album>) {
        Log.d(getString(R.string.TAG), getString(R.string.on_data_available, data))
        lastFmRecyclerViewAdapter.loadNewData(data)
    }

    override fun onError(e: Exception) {
        Log.d(getString(R.string.TAG), getString(R.string.on_error, e.message))
    }
}