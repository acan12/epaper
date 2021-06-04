package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.beelabs.com.codebase.base.BaseDialog
import app.beelabs.com.codebase.base.BaseFragment
import app.beelabs.com.codebase.base.response.BaseResponse
import app.epaper.com.bolang.App
import app.epaper.com.bolang.IConfig
import app.epaper.com.bolang.R
import app.epaper.com.bolang.databinding.FragmentReleaseEditionBinding
import app.epaper.com.bolang.model.entity.Content
import app.epaper.com.bolang.model.entity.reponse.ContentResponse
import app.epaper.com.bolang.presenter.ResourcePresenter
import app.epaper.com.bolang.presenter.manager.SessionManager
import app.epaper.com.bolang.ui.adapter.ReleaseEditionAdapter
import app.epaper.com.bolang.ui.dialog.SubscribeOfferDialog
import app.epaper.com.bolang.ui.dialog.SubscribeProcessingDialog
import app.epaper.com.bolang.ui.impl.IDialogSubscribeView
import app.epaper.com.bolang.ui.impl.IHomeView

class ReleaseEditionFragment : BaseFragment(), IHomeView, IDialogSubscribeView {
    private lateinit var binding: FragmentReleaseEditionBinding
    private var loading = false
    private var nextPage = 1
    private var totalData = 0

    private var query: String = ""
    private lateinit var sortby: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReleaseEditionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() = with(binding) {
        btnBack.setOnClickListener {
            App.getNavigationComponent().homeNavigation().navigateBackReleaseEdition(it)
        }
        inputSearchBox.doAfterTextChanged {
            nextPage = 1
            query = it.toString()
            doListReleaseEdition(query, sortby, nextPage)
        }
        btnSort.setOnClickListener {
            it.tag.apply {
                nextPage = 1
                sortby = this.toString()
                doListReleaseEdition(query, sortby, nextPage)

                if (sortby == IConfig.SORT_ASC) {
                    it.tag = IConfig.SORT_DESC
                    (it as TextView).setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_sort_asc,
                        0,
                        0,
                        0
                    );
                    it.text = resources.getString(R.string.oldest)
                } else {
                    it.tag = IConfig.SORT_ASC
                    (it as TextView).setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_sort_desc,
                        0,
                        0,
                        0
                    );
                    it.text = resources.getString(R.string.latest)
                }
            }
        }
        var layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvList.layoutManager = layoutManager
        rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    var visibleCount = (rvList.layoutManager)?.childCount!!
                    var totalCount = (rvList.layoutManager)?.itemCount!!
                    var passItem = layoutManager.findFirstCompletelyVisibleItemPosition()

                    if (!loading && (totalCount < totalData)) {
                        if (visibleCount + passItem >= totalCount) {
                            loading = true
                            itemProgressbar.visibility = View.VISIBLE
                            doListReleaseEdition(query, sortby, nextPage)
                        }
                    }
                }
            }
        })
        ResourcePresenter(this@ReleaseEditionFragment).onListContent()
    }

    private fun doListReleaseEdition(q: String, sortby: String, page: Int) {
        binding.itemProgressbar.visibility = View.VISIBLE
        ResourcePresenter(this@ReleaseEditionFragment).onListContent(q, sortby, page.toString())
    }

    override fun handleSuccess(data: BaseResponse) {
        var result = data as ContentResponse

        SessionManager.setSubscribe(result.meta.hasSubscribe, currentActivity)
        SessionManager.setSubscribeStatus(result.meta.transactionStatus, currentActivity)

        with(binding) {
            if (result.contents.isNotEmpty()) {
                rvList.visibility = View.VISIBLE
                itemProgressbar.visibility = View.GONE
                itemProgressbarMessage.visibility = View.GONE
                rvList.adapter = ReleaseEditionAdapter(result.contents, this@ReleaseEditionFragment)

                nextPage++
            } else {
                rvList.visibility = View.GONE
                itemProgressbar.visibility = View.INVISIBLE
                itemProgressbarMessage.text = resources.getString(R.string.data_not_found)
            }
        }
    }

    override fun showDetailEpaper(content: Content?, view: View) {
        if (!SessionManager.isLogin(view.context)) {
            SessionManager.setSkip(false, currentActivity)
            App.getNavigationComponent()
                .authNavigation()
                .navigateToLogin(view.context)
        } else if (!SessionManager.isSubscribe(view.context)) {
            if (SessionManager.getSubscribeStatus(view.context) == IConfig.STATUS_PENDING)
                SubscribeProcessingDialog(this, R.style.CoconutDialogFullScreen).show()
            else
                SubscribeOfferDialog(this, R.style.CoconutDialogFullScreen).show()
        } else {
            App.getNavigationComponent()
                .homeNavigation()
                .navigateReleaseEditionToPdfPreview(content!!, view)
        }
    }

    override fun handleButtonNextClicked(dialog: BaseDialog) {
        dialog.dismiss()
    }

    override fun handleButtonJoinClicked(dialog: BaseDialog) {
        App.getNavigationComponent().homeNavigation().navigateReleaseEditionToSubscribe(binding.root)
    }
}