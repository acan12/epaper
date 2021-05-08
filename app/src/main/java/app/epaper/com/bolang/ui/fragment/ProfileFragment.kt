package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.beelabs.com.codebase.base.BaseFragment
import app.epaper.com.bolang.App
import app.epaper.com.bolang.databinding.FragmentProfileBinding
import app.epaper.com.bolang.presenter.manager.SessionManager

class ProfileFragment : BaseFragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() = with(binding){
        btnLogout.setOnClickListener {
            SessionManager.clearSessionLogin(currentActivity)
            App.getNavigationComponent().authNavigation().navigateToLogin(currentActivity)
        }
    }
}