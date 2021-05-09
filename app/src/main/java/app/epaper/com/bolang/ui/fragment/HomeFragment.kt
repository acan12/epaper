package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import app.beelabs.com.codebase.base.BaseFragment
import app.beelabs.com.codebase.base.response.BaseResponse
import app.epaper.com.bolang.App
import app.epaper.com.bolang.IConfig.Companion.API_BASE_URL
import app.epaper.com.bolang.R
import app.epaper.com.bolang.databinding.FragmentHomeBinding
import app.epaper.com.bolang.model.entity.Content
import app.epaper.com.bolang.model.entity.reponse.ContentResponse
import app.epaper.com.bolang.presenter.ResourcePresenter
import app.epaper.com.bolang.presenter.manager.SessionManager
import app.epaper.com.bolang.ui.adapter.EpaperCardAdapter
import app.epaper.com.bolang.ui.dialog.SubscribeOfferDialog
import app.epaper.com.bolang.ui.impl.IHomeView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class HomeFragment : BaseFragment(), IHomeView {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {
        if (SessionManager.isLogin(root.context))
            avatarPersona.text = SessionManager.getPersonaFirstName(root.context)
        if (SessionManager.isSubscribe(root.context))
            imageSubscribeIndicator.setImageResource(R.drawable.ic_checked_green)

        avatarPersona.setOnClickListener {
            App.getNavigationComponent().homeNavigation().navigateToProfile(root, currentActivity)
        }

        var layout = LinearLayoutManager(activity)
        layout.orientation = LinearLayoutManager.HORIZONTAL
        rvEditionView.layoutManager = layout

        ResourcePresenter(this@HomeFragment).onListContent()
        swipeContainer.setOnRefreshListener {
            ResourcePresenter(this@HomeFragment).onListContent()
        }
    }

    fun showDetailEpaper(content: Content?, view: View) {
        if (!SessionManager.isLogin(view.context)) {
            App.getNavigationComponent()
                .authNavigation()
                .navigateToLogin(view.context)
        } else if (!SessionManager.isSubscribe(view.context)) {
            SubscribeOfferDialog(view, R.style.CoconutDialogFullScreen).show()
        } else {
            App.getNavigationComponent()
                .homeNavigation()
                .navigateToPreview(content!!, view)
        }
    }

    override fun handleSuccess(data: BaseResponse) {
        var resp = data as ContentResponse
        SessionManager.setSubscribe(resp.meta.hasSubscribe, currentActivity)
        if (resp.contents != null) {
            with(binding) {
                var contents = resp.contents.reversed()
                swipeContainer.setRefreshing(false);
                Glide.with(requireActivity())
                    .load(API_BASE_URL + contents[0].cover_image_url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mainContentImage)

                itemProgressbar.visibility = View.GONE
                mainContentImage.setOnClickListener { showDetailEpaper(contents[0], root) }

                rvEditionView.adapter = EpaperCardAdapter(contents, this@HomeFragment)

            }
        }
    }
}