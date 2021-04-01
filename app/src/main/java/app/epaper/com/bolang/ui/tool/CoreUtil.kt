package app.epaper.com.bolang.ui.tool

import android.content.Context
import android.os.Environment
import android.util.Log
import app.epaper.com.bolang.R
import java.io.File

object CoreUtil {

    fun buildPdfDownloader(context: Context, url: String, docId: Int, listener: PdfDownloader.StatusListener){
        var fileName = context.resources.getString(R.string.pdf_filename_ext).replace("[docid]", docId.toString())
        PdfDownloader(url, fileName, listener)
    }

    fun savedOffline(fileName: String, context: Context) {
        setupAppDir(context)
        val from = File(
            context.externalCacheDir?.absolutePath + File.separator+fileName
        )
        val to = File(
            Environment.getExternalStorageDirectory().absolutePath + "/${context.resources.getString(R.string.app_name)}/$fileName"
        )
        from.renameTo(to)
        Log.d("PATH_NOW", to.absolutePath)
    }

    private fun setupAppDir(context: Context) {
        var file = File(
            Environment.getExternalStorageDirectory().absolutePath,
            context.resources.getString(R.string.app_name)
        )
        if(!file.mkdirs()) file.mkdirs()
    }

}