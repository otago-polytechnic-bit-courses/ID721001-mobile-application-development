package op.graysono.practical03.helpers

import android.os.AsyncTask
import op.graysono.practical03.enums.DownloadStatus.NONE
import op.graysono.practical03.enums.DownloadStatus.OK
import op.graysono.practical03.interfaces.IDataDownloadComplete
import java.net.URL

class RawDataAsyncTask(private val listener: IDataDownloadComplete) :
    AsyncTask<String, Void, String>() {
    private var downloadStatus = NONE

    private fun downloadXML(urlPath: String?): String {
        return URL(urlPath).readText()
    }

    override fun doInBackground(vararg url: String?): String {
        var data = ""
        try {
            downloadStatus = OK
            data = downloadXML(url[0])
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return data
    }

    override fun onPostExecute(result: String) {
        listener.onDownloadComplete(result, downloadStatus)
    }
}