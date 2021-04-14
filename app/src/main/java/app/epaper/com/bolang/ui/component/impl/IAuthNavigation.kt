package app.epaper.com.bolang.ui.component.impl

import android.content.Context
import android.view.View

interface IAuthNavigation : IBaseNavigation {

    fun navigateToLogin(context: Context)

    fun navigateToSignupForm(view: View)

    fun navigateToSubscribe(view: View)
}