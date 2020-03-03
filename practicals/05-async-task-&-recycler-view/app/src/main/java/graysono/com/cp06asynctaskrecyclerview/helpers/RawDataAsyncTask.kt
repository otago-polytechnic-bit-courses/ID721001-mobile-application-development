package graysono.com.cp06asynctaskrecyclerview.helpers

import android.os.AsyncTask
import graysono.com.cp06asynctaskrecyclerview.enums.DownloadStatus
import graysono.com.cp06asynctaskrecyclerview.interfaces.IDataDownloadComplete
import java.lang.Exception
import java.net.URL

class RawDataAsyncTask(private val listener: IDataDownloadComplete) :
    AsyncTask<String, Void, String>() {
    private var downloadStatus = DownloadStatus.NONE

    override fun onPostExecute(result: String) {
        listener.onDownloadComplete(result, downloadStatus)
    }

    override fun doInBackground(vararg url: String?): String {
        var data = ""
        try {
            downloadStatus = DownloadStatus.OK
            data = downloadXML(url[0])
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return data
    }

    private fun downloadXML(urlPath: String?): String {
        return URL(urlPath).readText()
    }
}


