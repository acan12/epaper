package app.epaper.com.bolang.ui.component.manager

import android.content.Context
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import app.epaper.com.bolang.IConfig
import app.epaper.com.bolang.IConfig.Companion.API_BASE_URL
import app.epaper.com.bolang.R
import app.epaper.com.bolang.model.entity.Content
import app.epaper.com.bolang.ui.component.impl.IHomeNavigation

class HomeNavigation : IHomeNavigation {
    override fun navigateToPreview(item: Content, view: View) {
        var bundle = bundleOf(
            IConfig.ARG_ID_PDF to item.id,
            IConfig.ARG_NAME_PDF to item.title,
            IConfig.ARG_URL_PDF_LINK to API_BASE_URL + item.pdf_doc
        )
        Navigation.findNavController(view)
            .navigate(R.id.action_homeFragment_to_pdfPreviewFragment, bundle)
    }

    override fun navigateReleaseEditionToPdfPreview(item: Content, view: View) {
        var bundle = bundleOf(
            IConfig.ARG_ID_PDF to item.id,
            IConfig.ARG_NAME_PDF to item.title,
            IConfig.ARG_URL_PDF_LINK to API_BASE_URL + item.pdf_doc
        )
        Navigation.findNavController(view)
            .navigate(R.id.action_releaseEditionFragment_to_pdfPreviewFragment, bundle)
    }

    override fun navigateLoginToHome(view: View, context: Context) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
    }

    override fun navigateToSignup(view: View, context: Context) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signupFragment)
    }

    override fun navigateToSubscribe(view: View, context: Context) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_subscribeFragment)
    }

    override fun navigateToProfile(view: View, context: Context) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_profileFragment)
    }

    override fun navigatePreviewToHome(view: View, context: Context) {
        Navigation.findNavController(view).navigate(R.id.action_pdfPreviewFragment_to_homeFragment2)
    }

    override fun navigateFromSubscribeToHome(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_subscribeFragment_to_homeFragment)
    }

    override fun navigateProfileToHome(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_homeFragment)
    }

    override fun navigateHomeToReleaseEdition(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_releaseEditionFragment)
    }

    override fun navigateBackReleaseEdition(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_releaseEditionFragment_to_homeFragment)
    }

    override fun navigateReleaseEditionToSubscribe(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_releaseEditionFragment_to_subscribeFragment)
    }
}