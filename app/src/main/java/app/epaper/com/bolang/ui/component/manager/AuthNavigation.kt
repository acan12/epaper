package app.epaper.com.bolang.ui.component.manager

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.navigation.Navigation
import app.epaper.com.bolang.R
import app.epaper.com.bolang.ui.activity.AuthActivity
import app.epaper.com.bolang.ui.component.impl.IAuthNavigation
import app.epaper.com.bolang.ui.component.impl.IHomeNavigation

class AuthNavigation : IAuthNavigation {

    override fun navigateToLogin(context: Context) {
        context.startActivity(Intent(context, AuthActivity::class.java))
        (context as Activity).finish()
    }

    override fun navigateToSignupForm(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signupFragment)
    }
}