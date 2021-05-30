package app.epaper.com.bolang.ui.component.impl

import android.content.Context
import android.view.View
import app.epaper.com.bolang.model.entity.Content
import app.epaper.com.bolang.model.entity.Epaper

interface IHomeNavigation : IBaseNavigation {

    fun navigateLoginToHome(view: View, context: Context)

    fun navigatePreviewToHome(view: View, context: Context)

    fun navigateToSignup(view: View, context: Context)

    fun navigateToSubscribe(view: View, context: Context)

    fun navigateToPreview(item: Content, view: View)

    fun navigateFromSubscribeToHome(view: View)

    fun navigateToProfile(view: View, context: Context)

    fun navigateProfileToHome(view: View)

    fun navigateHomeToListEdition(view: View)

    fun navigateBackListEdition(view: View)
}