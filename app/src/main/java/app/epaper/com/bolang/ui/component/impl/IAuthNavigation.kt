package app.epaper.com.bolang.ui.component.impl

import android.content.Context
import android.view.View
import app.epaper.com.bolang.model.entity.request.SignupRequest

interface IAuthNavigation : IBaseNavigation {

    fun navigateToLogin(context: Context)

    fun navigateToSignupForm(view: View)

    fun navigateToPassword(request: SignupRequest, view: View)

    fun navigatePasswordToLogin(view: View)
}
