package app.epaper.com.bolang.ui.component.impl

import android.content.Context
import android.view.View

interface IHomeNavigation : IBaseNavigation {

    fun navigateToHome(view: View, context: Context)

    fun navigateToSignup(view: View, context: Context)

    fun navigateToSubscribe(view: View, context: Context)

    fun navigateToPreview(context: Context)
}