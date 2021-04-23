package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.beelabs.com.codebase.base.BaseFragment
import app.epaper.com.bolang.App
import app.epaper.com.bolang.databinding.FragmentHomeBinding
import app.epaper.com.bolang.databinding.FragmentLoginBinding
import app.epaper.com.bolang.databinding.FragmentPasswordBinding
import app.epaper.com.bolang.databinding.FragmentSignupBinding

class PasswordFragment : BaseFragment() {
    private lateinit var binding: FragmentPasswordBinding

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

    private fun setupUI() = with(binding){
        btnNext.setOnClickListener {
            App.getNavigationComponent().authNavigation().navigatePasswordToLogin(root)
        }
    }
}