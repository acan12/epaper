package app.epaper.com.bolang.ui.tool

import android.content.Context
import android.view.View
import app.beelabs.com.codebase.IConfig
import app.epaper.com.bolang.App
import app.epaper.com.bolang.R
import app.epaper.com.bolang.model.entity.Content
import app.epaper.com.bolang.model.entity.Epaper
import app.epaper.com.bolang.presenter.manager.SessionManager
import app.epaper.com.bolang.ui.dialog.SubscribeOfferDialog
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object UiUtil {

    fun convertToCurrencyFormat(amount: Int?): String {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.maximumFractionDigits = 0
        return numberFormat.format(amount).toString().replace("Rp", "Rp ")
    }


    fun convertStringToDateWithFormat(
        dateInput: String?,
        inputTime: String?,
        outputTime: String?
    ): String? {
        val date1 = dateInput?.replace("\\.\\d+".toRegex(), "")
        val tz = TimeZone.getTimeZone("GMT+07:00")
        var timeDisplay: String? = null
        val calendar = Calendar.getInstance(tz)
        val inputFormatTime = SimpleDateFormat(inputTime, Locale.US)
        val outputFormatTime = SimpleDateFormat(outputTime, Locale.US)
        outputFormatTime.calendar = calendar
        var date: Date? = null
        var dateString = ""
        if (date1 != null) {
            try {
                date = inputFormatTime.parse(date1)
                calendar.time = date
                timeDisplay = outputFormatTime.format(calendar.time)
                for (month in IConfig.monthLabels) {
                    val labels = month.split("_").toTypedArray()
                    dateString = timeDisplay.replace(labels[0].toRegex(), labels[1])
                    if (dateString != timeDisplay) break
                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        return dateString
    }
}