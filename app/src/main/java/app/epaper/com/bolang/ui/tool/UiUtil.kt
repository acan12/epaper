package app.epaper.com.bolang.ui.tool

import android.app.Activity
import android.view.View

object UiUtil {

    fun setupLightStatusBarMode(activity: Activity) {
        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}