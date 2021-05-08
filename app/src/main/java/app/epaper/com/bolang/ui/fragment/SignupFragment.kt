package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import app.beelabs.com.codebase.base.BaseFragment
import app.epaper.com.bolang.App
import app.epaper.com.bolang.databinding.FragmentHomeBinding
import app.epaper.com.bolang.databinding.FragmentLoginBinding
import app.epaper.com.bolang.databinding.FragmentSignupBinding
import app.epaper.com.bolang.model.entity.request.SignupRequest

class SignupFragment : BaseFragment() {
    private lateinit var binding: FragmentSignupBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding){

        checkButtonValidation()
        btnNext.setOnClickListener {
            var signupRequest = SignupRequest(
                inputUsername.text.toString(),
                inputEmail.text.toString(),
                inputPhone.text.toString(),
                inputAddress.text.toString(),
                ""
            )
            App.getNavigationComponent().authNavigation().navigateToPassword(signupRequest, root)
        }
    }

    private fun checkButtonValidation() = with(binding) {
        inputUsername.doAfterTextChanged {
            btnNext.isEnabled =
                (inputUsername.length() > 1 && inputEmail.length() > 0
                        && inputPhone.length() > 0 && inputAddress.length() > 0)
        }

        inputEmail.doAfterTextChanged {
            btnNext.isEnabled =
                (inputUsername.length() > 1 && inputEmail.length() > 0
                        && inputPhone.length() > 0 && inputAddress.length() > 0)
        }

        inputPhone.doAfterTextChanged {
            btnNext.isEnabled =
                (inputUsername.length() > 1 && inputEmail.length() > 0
                        && inputPhone.length() > 0 && inputAddress.length() > 0)
        }

        inputAddress.doAfterTextChanged {
            btnNext.isEnabled =
                (inputUsername.length() > 1 && inputEmail.length() > 0
                        && inputPhone.length() > 0 && inputAddress.length() > 0)
        }
    }
}