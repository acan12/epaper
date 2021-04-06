package app.epaper.com.bolang.ui.component.manager

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import app.epaper.com.bolang.App
import app.epaper.com.bolang.R
import app.epaper.com.bolang.ui.activity.MainActivity
import app.epaper.com.bolang.ui.component.impl.IHomeNavigation

class HomeNavigation : IHomeNavigation {
    override fun navigateToPreview(context: Context) {
        context.startActivity(Intent(context, MainActivity::class.java))
    }

    override fun navigateToHome(view: View, context: Context) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
    }

    override fun navigateToSignup(view: View, context: Context) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signupFragment)
    }

    override fun navigateToSubscribe(view: View, context: Context) {
        Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_subscribeFragment)
    }
}