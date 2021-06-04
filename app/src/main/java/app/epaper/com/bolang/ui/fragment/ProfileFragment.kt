package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.beelabs.com.codebase.base.BaseFragment
import app.beelabs.com.codebase.base.response.BaseResponse
import app.epaper.com.bolang.App
import app.epaper.com.bolang.R
import app.epaper.com.bolang.databinding.FragmentProfileBinding
import app.epaper.com.bolang.model.entity.reponse.ProfileResponse
import app.epaper.com.bolang.presenter.UserPresenter
import app.epaper.com.bolang.presenter.manager.SessionManager
import app.epaper.com.bolang.ui.impl.IProfileView

class ProfileFragment : BaseFragment(), IProfileView {
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

    private fun setupUI() = with(binding) {
        if (SessionManager.isSubscribe(currentActivity)) itemStatusSubscribe.setTextColor(
            resources.getColor(
                R.color.color_blue_188fa7
            )
        )

        UserPresenter(this@ProfileFragment).onProfile(SessionManager.getPersonaId(currentActivity))
        btnBack.setOnClickListener {
            App.getNavigationComponent().homeNavigation().navigateProfileToHome(root)
        }
        btnLogout.setOnClickListener {
            SessionManager.clearSessionLogin(currentActivity)
            App.getNavigationComponent().authNavigation().navigateToLogin(currentActivity)
        }
    }

    override fun handleProfileSuccess(data: BaseResponse) {
        var resp = data as ProfileResponse
        if (resp.data != null) {
            with(binding) {
                var profile = resp.data
                avatarPersona.text = profile.name[0].toString().toUpperCase()
                itemUsername.text = profile.name
                itemPhone.text = profile.phone
                itemAddress.text = profile.address
                itemEmail.text = profile.email
                if (!profile.has_subscribe)
                    itemStatusSubscribe.setTextColor(resources.getColor(R.color.color_grey_999999))
            }
        }
    }
}