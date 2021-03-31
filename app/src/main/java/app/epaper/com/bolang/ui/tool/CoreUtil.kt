package app.epaper.com.bolang.ui.tool

import android.content.Context
import android.os.Environment
import android.util.Log
import app.epaper.com.bolang.R
import java.io.File

object CoreUtil {

    fun savedOffline(fileName: String, context: Context) {
        val from = File(
            context.externalCacheDir?.absolutePath + fileName
        )
        val to = File(
            Environment.getExternalStorageDirectory().absolutePath + "/${context.resources.getString(R.string.app_name)}/$fileName"
        )
        from.renameTo(to)
        Log.d("PATH_NOW", to.absolutePath)
    }

}