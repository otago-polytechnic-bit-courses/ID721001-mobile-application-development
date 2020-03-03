package graysono.com.cp06asynctaskrecyclerview.activities

import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import graysono.com.cp06asynctaskrecyclerview.R
import graysono.com.cp06asynctaskrecyclerview.enums.DownloadStatus
import graysono.com.cp06asynctaskrecyclerview.helpers.*
import graysono.com.cp06asynctaskrecyclerview.interfaces.IDataDownloadAvailable
import graysono.com.cp06asynctaskrecyclerview.interfaces.IDataDownloadComplete
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity(), IDataDownloadAvailable,
    IDataDownloadComplete {

    private lateinit var rawDataAsyncTask: RawDataAsyncTask
    private lateinit var lastFmRecyclerViewAdapter: LastFmRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayToolbar(false)

        lastFmRecyclerViewAdapter = LastFmRecyclerViewAdapter(ArrayList())
        revAlbums.layoutManager = LinearLayoutManager(this@MainActivity)
        revAlbums.adapter = lastFmRecyclerViewAdapter

        val url: String = createURI(getString(R.string.base_url), getString(R.string.method),
            "beyonce", getString(R.string.api_key), getString(R.string.format))
        rawDataAsyncTask = RawDataAsyncTask(this)
        rawDataAsyncTask.execute(url)
    }

    private fun createURI(
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
        if (status == DownloadStatus.OK) {
            val lastFmAsyncTask = LastFmAsyncTask(this)
            lastFmAsyncTask.execute(data)
        }
    }
}

