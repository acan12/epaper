package app.epaper.com.bolang.ui.tool

import android.content.Context
import android.view.View
import app.epaper.com.bolang.App
import app.epaper.com.bolang.R
import app.epaper.com.bolang.model.entity.Epaper
import app.epaper.com.bolang.presenter.manager.SessionManager
import app.epaper.com.bolang.ui.dialog.SubscribeOfferDialog
import java.text.NumberFormat
import java.util.*

object UiUtil {

    fun convertToCurrencyFormat(amount: Int?): String {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.maximumFractionDigits = 0
        return numberFormat.format(amount).toString().replace("Rp", "Rp ")
    }

    fun showDetailEpaper(epaper: Epaper?, view: View) {
        if (!SessionManager.isLogin(view.context)) {
            App.getNavigationComponent()
                .authNavigation()
                .navigateToLogin(view.context)
        } else if (!SessionManager.isSubscribe(view.context)) {
            SubscribeOfferDialog(view, R.style.CoconutDialogFullScreen).show()
        } else {
            App.getNavigationComponent()
                .homeNavigation()
                .navigateToPreview(epaper!!, view)
        }
    }
}