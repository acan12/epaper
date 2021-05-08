package app.epaper.com.bolang.ui.component.impl

import android.content.Context
import android.view.View
import app.epaper.com.bolang.model.entity.Epaper

interface IHomeNavigation : IBaseNavigation {

    fun navigateToHome(view: View, context: Context)

    fun navigatePreviewToHome(view: View, context: Context)

    fun navigateToSignup(view: View, context: Context)

    fun navigateToSubscribe(view: View, context: Context)

    fun navigateToPreview(item: Epaper, view: View)

    fun navigateFromSubscribeToHome(view: View, context: Context)

    fun navigateToProfile(view: View, context: Context)
}