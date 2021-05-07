package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.beelabs.com.codebase.base.BaseFragment
import app.beelabs.com.codebase.base.response.BaseResponse
import app.epaper.com.bolang.App
import app.epaper.com.bolang.databinding.FragmentLoginBinding
import app.epaper.com.bolang.model.entity.reponse.LoginResponse
import app.epaper.com.bolang.model.entity.request.LoginRequest
import app.epaper.com.bolang.presenter.AuthPresenter
import app.epaper.com.bolang.presenter.manager.SessionManager
import app.epaper.com.bolang.ui.impl.IAuthView

class LoginFragment : BaseFragment(), IAuthView {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {
        if(SessionManager.isLogin(root.context)) App.getNavigationComponent().homeNavigation().navigateToHome(root, currentActivity)
        headerDescription.setOnClickListener {
            App.getNavigationComponent().authNavigation().navigateToSignupForm(root)
        }
        btnNext.setOnClickListener {
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()
            AuthPresenter(this@LoginFragment).onLogin(LoginRequest(email, password))
        }
    }


    override fun handleSuccess(resp: BaseResponse) {
        if (resp != null) {
            val data = (resp as LoginResponse)
            SessionManager.apply {
                putPersonaFirstName(data.data.name[0].toString(), currentActivity)
                putCredential(data.token, currentActivity)
                isSubscribe(data.data.has_subscribe, currentActivity)
            }

            App.getNavigationComponent().homeNavigation()
                .navigateToHome(binding.root, currentActivity)
        }

    }
}