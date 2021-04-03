package app.epaper.com.bolang.ui.component.manager

import android.app.Activity
import android.content.Context
import android.content.Intent
import app.epaper.com.bolang.ui.activity.AuthActivity
import app.epaper.com.bolang.ui.component.impl.IAuthNavigation
import app.epaper.com.bolang.ui.component.impl.IHomeNavigation

class AuthNavigation : IAuthNavigation {

    override fun navigateToLogin(context: Context) {
        context.startActivity(Intent(context, AuthActivity::class.java))
        (context as Activity).finish()
    }
}