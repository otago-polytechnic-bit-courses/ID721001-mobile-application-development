package op.graysono.practical03.interfaces

import op.graysono.practical03.helpers.Album

interface IDataDownloadAvailable {
    fun onDataAvailable(data: ArrayList<Album>)
    fun onError(e: Exception)
}