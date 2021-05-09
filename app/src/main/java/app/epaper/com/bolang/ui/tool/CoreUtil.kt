package app.epaper.com.bolang.ui.tool

import android.content.Context
import android.os.Environment
import app.epaper.com.bolang.R
import app.epaper.com.bolang.model.entity.request.TransactionRequest
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.io.IOException

object CoreUtil {

    fun getFileSaved(fileName: String, context: Context): File = File(
        Environment.getExternalStorageDirectory().absolutePath + "/${context.resources.getString(
            R.string.app_name
        )}/$fileName"
    )

    fun savedOffline(fileName: String, context: Context) {
        setupAppDir(context)
        val from = File(
            context.externalCacheDir?.absolutePath + File.separator + fileName
        )
        val to = File(
            Environment.getExternalStorageDirectory().absolutePath + "/${context.resources.getString(
                R.string.app_name
            )}/$fileName"
        )
        from.renameTo(to)
    }

    private fun setupAppDir(context: Context) {
        var file = File(
            Environment.getExternalStorageDirectory().absolutePath,
            context.resources.getString(R.string.app_name)
        )
        if (!file.mkdirs()) file.mkdirs()
    }

    fun convertObjectToJsonString(obj: Any): String{
        var mapper = ObjectMapper()
        return mapper.writeValueAsString(obj)
    }

    fun <T> getObjectFromJsonStringCache(jsonString: String, clazz: Class<T>?): Any{
        var mapper = ObjectMapper()
        return mapper.readValue(jsonString, clazz)!!
    }
}