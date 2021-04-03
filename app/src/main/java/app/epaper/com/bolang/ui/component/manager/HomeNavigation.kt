package app.epaper.com.bolang.ui.component.manager

import android.content.Context
import android.content.Intent
import app.epaper.com.bolang.ui.activity.MainActivity
import app.epaper.com.bolang.ui.component.impl.IHomeNavigation

class HomeNavigation : IHomeNavigation {
    override fun navigateToPreview(context: Context) {
        context.startActivity(Intent(context, MainActivity::class.java))
    }

}