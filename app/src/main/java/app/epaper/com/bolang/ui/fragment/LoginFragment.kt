package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.beelabs.com.codebase.base.BaseFragment
import app.beelabs.com.codebase.base.contract.IView
import app.epaper.com.bolang.App
import app.epaper.com.bolang.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment(), IView {
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
        headerDescription.setOnClickListener {
            App.getNavigationComponent().authNavigation().navigateToSignupForm(root)
        }
        btnNext.setOnClickListener {
            App.getNavigationComponent().homeNavigation()
                .navigateToHome(root, requireContext())
        }
    }
}