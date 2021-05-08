package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.navArgs
import app.beelabs.com.codebase.base.BaseFragment
import app.beelabs.com.codebase.base.response.BaseResponse
import app.epaper.com.bolang.App
import app.epaper.com.bolang.databinding.FragmentPasswordBinding
import app.epaper.com.bolang.model.entity.request.SignupRequest
import app.epaper.com.bolang.presenter.AuthPresenter
import app.epaper.com.bolang.ui.impl.IAuthView

class PasswordFragment : BaseFragment(), IAuthView {
    private lateinit var binding: FragmentPasswordBinding
    private val args by navArgs<PasswordFragmentArgs>()
    lateinit var request: SignupRequest

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {
        if (!arguments?.isEmpty!!) {
            request = args.signupData!!
        }
        inputPassword.doAfterTextChanged {
            btnNext.isEnabled =
                (inputPassword.length() > 1 && inputConfirmPassword.length() > 0)
        }
        inputConfirmPassword.doAfterTextChanged {
            btnNext.isEnabled =
                (inputPassword.length() > 1 && inputConfirmPassword.length() > 0)
        }

        btnNext.setOnClickListener {
            val password = inputPassword.text.toString()
            val confPassword = inputConfirmPassword.text.toString()
            if (password != confPassword) Toast.makeText(
                currentActivity,
                "Invalid password",
                Toast.LENGTH_SHORT
            ).show()
            else
                request.password = password
            AuthPresenter(this@PasswordFragment).onSignupUser(request)
        }
    }

    override fun handleSuccess(data: BaseResponse) {
        App.getNavigationComponent().authNavigation().navigatePasswordToLogin(binding.root)
    }
}