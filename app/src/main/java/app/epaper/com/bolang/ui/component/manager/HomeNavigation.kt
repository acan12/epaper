package app.epaper.com.bolang.ui.component.manager

import android.content.Context
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import app.epaper.com.bolang.IConfig
import app.epaper.com.bolang.R
import app.epaper.com.bolang.model.entity.Epaper
import app.epaper.com.bolang.ui.component.impl.IHomeNavigation

class HomeNavigation : IHomeNavigation {
    override fun navigateToPreview(item: Epaper, view: View) {
        var bundle = bundleOf(IConfig.ARG_ID_PDF to item.id,
            IConfig.ARG_NAME_PDF to item.name,
            IConfig.ARG_URL_PDF_LINK to item.urlEpaper)
        Navigation.findNavController(view)
            .navigate(R.id.action_homeFragment_to_pdfPreviewFragment, bundle)
    }

    override fun navigateToHome(view: View, context: Context) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
    }

    override fun navigateToSignup(view: View, context: Context) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signupFragment)
    }

    override fun navigateToSubscribe(view: View, context: Context) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_subscribeFragment)
    }

    override fun navigatePreviewToHome(view: View, context: Context) {
        Navigation.findNavController(view).navigate(R.id.action_pdfPreviewFragment_to_homeFragment2)
    }

    override fun navigateFromSubscribeToHome(view: View, context: Context) {
        Navigation.findNavController(view).navigate(R.id.action_subscribeFragment_to_homeFragment)
    }
}