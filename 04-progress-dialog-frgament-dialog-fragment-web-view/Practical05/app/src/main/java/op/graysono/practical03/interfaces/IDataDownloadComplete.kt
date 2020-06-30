package op.graysono.practical03.interfaces

import op.graysono.practical03.enums.DownloadStatus

interface IDataDownloadComplete {
    fun onDownloadComplete(data: String, status: DownloadStatus)
}