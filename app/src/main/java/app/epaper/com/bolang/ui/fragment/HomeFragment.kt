package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import app.beelabs.com.codebase.base.BaseDialog
import app.beelabs.com.codebase.base.BaseFragment
import app.beelabs.com.codebase.base.response.BaseResponse
import app.epaper.com.bolang.App
import app.epaper.com.bolang.IConfig
import app.epaper.com.bolang.IConfig.Companion.API_BASE_URL
import app.epaper.com.bolang.IConfig.Companion.STATUS_PENDING
import app.epaper.com.bolang.R
import app.epaper.com.bolang.databinding.FragmentHomeBinding
import app.epaper.com.bolang.model.entity.Content
import app.epaper.com.bolang.model.entity.reponse.ContentResponse
import app.epaper.com.bolang.presenter.ResourcePresenter
import app.epaper.com.bolang.presenter.manager.SessionManager
import app.epaper.com.bolang.ui.adapter.EpaperCardAdapter
import app.epaper.com.bolang.ui.dialog.SubscribeOfferDialog
import app.epaper.com.bolang.ui.dialog.SubscribeProcessingDialog
import app.epaper.com.bolang.ui.impl.IDialogSubscribeView
import app.epaper.com.bolang.ui.impl.IHomeView
import app.epaper.com.bolang.ui.tool.UiUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), IHomeView, IDialogSubscribeView {
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
        showHeaderInfo()
        btnShowAll.setOnClickListener {
            App.getNavigationComponent().homeNavigation().navigateHomeToReleaseEdition(it)
        }
        avatarPersona.setOnClickListener {
            App.getNavigationComponent().homeNavigation().navigateToProfile(root, currentActivity)
        }

        var layout = LinearLayoutManager(activity)
        layout.orientation = LinearLayoutManager.HORIZONTAL
        rvEditionView.layoutManager = layout

        ResourcePresenter(this@HomeFragment).onListContent()
    }

    private fun showHeaderInfo() = with(binding) {
        if (SessionManager.isLogin(root.context))
            avatarPersona.text = SessionManager.getPersonaUserName(root.context)[0].toUpperCase().toString()
        else {
            avatarPersona.visibility = View.INVISIBLE
            imageSubscribeIndicator.visibility = View.INVISIBLE
        }
        if (SessionManager.isSubscribe(root.context))
            imageSubscribeIndicator.setImageResource(R.drawable.ic_checked_green)
        else
            imageSubscribeIndicator.setImageResource(R.drawable.ic_checked_grey)

    }

    override fun showDetailEpaper(content: Content?, view: View) {
        if (!SessionManager.isLogin(view.context)) {
            SessionManager.setSkip(false, currentActivity)
            App.getNavigationComponent()
                .authNavigation()
                .navigateToLogin(view.context)
        } else if (!SessionManager.isSubscribe(view.context)) {
            if (SessionManager.getSubscribeStatus(view.context) == STATUS_PENDING)
                SubscribeProcessingDialog(this, R.style.CoconutDialogFullScreen).show()
            else
                SubscribeOfferDialog(this, R.style.CoconutDialogFullScreen).show()
        } else {
            App.getNavigationComponent()
                .homeNavigation()
                .navigateToPreview(content!!, view)
        }
    }

    override fun handleSuccess(data: BaseResponse) {
        var resp = data as ContentResponse
        SessionManager.setSubscribe(resp.meta.hasSubscribe, currentActivity)
        SessionManager.setSubscribeStatus(resp.meta.transactionStatus, currentActivity)

        showHeaderInfo()
        with(binding) {
            if (resp.contents.isNotEmpty()) {

                var contents = resp.contents.reversed()
                Glide.with(requireActivity())
                    .load(API_BASE_URL + contents[0].cover_image_url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mainContentImage)

                itemProgressbar.visibility = View.GONE
                itemProgressbarMessage.visibility = View.GONE
                mainEditionDate.text = UiUtil.convertStringToDateWithFormat(
                    contents[0].release_date,
                    IConfig.yyyy_MM_dd,
                    IConfig.dd_MMM_yyyy
                )
                btnDetail.setOnClickListener { showDetailEpaper(contents[0], root) }
                mainContentImage.setOnClickListener { showDetailEpaper(contents[0], root) }

                if(contents.size > 1) {
                    rvEditionView.adapter = EpaperCardAdapter(contents, this@HomeFragment)
                    slideIndicator.attachToRecyclerView(rvEditionView)
                } else {
                    slideLayout.visibility = View.GONE
                }

            } else {
                itemProgressbar.visibility = View.INVISIBLE
                itemProgressbarMessage.text = resources.getString(R.string.data_not_found)
                slideLayout.visibility = View.GONE
            }
        }
    }

    override fun handleButtonNextClicked(dialog: BaseDialog) {
        dialog.dismiss()
    }

    override fun handleButtonJoinClicked(dialog: BaseDialog) {
        App.getNavigationComponent()
            .homeNavigation()
            .navigateToSubscribe(binding.root, currentActivity)
    }
}