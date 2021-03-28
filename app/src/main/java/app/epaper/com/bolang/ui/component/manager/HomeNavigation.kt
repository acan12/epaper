package app.epaper.com.bolang.ui.component.manager

import android.content.Context
import android.content.Intent
import app.epaper.com.bolang.ui.component.impl.IHomeNavigation

class HomeNavigation : IHomeNavigation {
    override fun goSecondPage(txt: String, context: Context) {
        val intent = Intent()
        context.startActivity(intent)
    }

}