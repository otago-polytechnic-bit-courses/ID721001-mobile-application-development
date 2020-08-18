package op.graysono.practical03.helpers

import android.os.AsyncTask
import op.graysono.practical03.interfaces.IDataDownloadAvailable
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class LastFmAsyncTask(private val listener: IDataDownloadAvailable) :
    AsyncTask<String, Void, ArrayList<Album>>() {
    override fun onPostExecute(result: ArrayList<Album>) {
        super.onPostExecute(result)
        listener.onDataAvailable(result)
    }

    override fun doInBackground(vararg url: String): ArrayList<Album> {
        val albums = ArrayList<Album>()
        try {
            val jsonData = JSONObject(url[0])
            val topAlbumsObj: JSONObject = jsonData.getJSONObject("topalbums")
            val albumItems: JSONArray = topAlbumsObj.getJSONArray("album")

            for (albumItem: Int in 0 until albumItems.length()) {
                val albumObj: JSONObject = albumItems.getJSONObject(albumItem)
                val name: String = albumObj.getString("name")
                val url: String = albumObj.getString("url")
                val imageItems: JSONArray = albumObj.getJSONArray("image")
                val imageObj: JSONObject = imageItems.getJSONObject(3)
                val imageText: String = imageObj.getString("#text")
                val playCount: Int = albumObj.getInt("playcount")
                if (imageText.isNotEmpty()) {
                    val album = Album(name, imageText, playCount, url)
                    albums.add(album)
                }
            }
        } catch (e: Exception) {
            cancel(true)
            listener.onError(e)
        }
        return albums
    }
}
