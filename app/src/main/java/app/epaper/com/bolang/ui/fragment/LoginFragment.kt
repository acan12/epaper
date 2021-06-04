package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
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
        if(SessionManager.isLogin(root.context) || SessionManager.isSkip(root.context))
            App.getNavigationComponent().homeNavigation().navigateLoginToHome(root, currentActivity)

        headerDescription.setOnClickListener {
            App.getNavigationComponent().authNavigation().navigateToSignupForm(root)
        }

        inputEmail.doAfterTextChanged {
            btnSubmit.isEnabled = (inputEmail.length() > 1 && inputPassword.length() > 0)
        }

        inputPassword.doAfterTextChanged {
            btnSubmit.isEnabled = (inputEmail.length() > 1 && inputPassword.length() > 0)
        }

        btnSkip.setOnClickListener {
            SessionManager.setSkip(true, currentActivity)
            App.getNavigationComponent().homeNavigation()
                .navigateLoginToHome(binding.root, currentActivity)
        }

        btnSubmit.setOnClickListener {
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            inputEmail.setText("")
            inputPassword.setText("")
            AuthPresenter(this@LoginFragment).onLogin(LoginRequest(email, password))
        }
    }


    override fun handleSuccess(resp: BaseResponse) {
        if (resp != null) {
            val data = (resp as LoginResponse)
            SessionManager.apply {
                putPersonaId(data.user.id, currentActivity)
                putPersonaUserName(data.user.name, currentActivity)
                putCredential(data.token, currentActivity)
                setSubscribe(data.user.has_subscribe, currentActivity)
            }

            App.getNavigationComponent().homeNavigation()
                .navigateLoginToHome(binding.root, currentActivity)
        }

    }
}