package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.beelabs.com.codebase.base.BaseFragment
import app.beelabs.com.codebase.base.response.BaseResponse
import app.epaper.com.bolang.App
import app.epaper.com.bolang.R
import app.epaper.com.bolang.databinding.FragmentListPdfeditionBinding
import app.epaper.com.bolang.model.entity.Content
import app.epaper.com.bolang.model.entity.reponse.ContentResponse
import app.epaper.com.bolang.presenter.ResourcePresenter
import app.epaper.com.bolang.presenter.manager.SessionManager
import app.epaper.com.bolang.ui.adapter.EpaperEditionAdapter
import app.epaper.com.bolang.ui.dialog.SubscribeOfferDialog
import app.epaper.com.bolang.ui.impl.IHomeView

class ListPdfEditionFragment : BaseFragment(), IHomeView {
    private lateinit var binding : FragmentListPdfeditionBinding
    private var loading = false
    private var nextPage = 1
    private var totalData = 0

    private lateinit var query: String
    private lateinit var sortby: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListPdfeditionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() = with(binding){
        btnBack.setOnClickListener {
            App.getNavigationComponent().homeNavigation().navigateBackListEdition(it)
        }
        btnSort.setOnClickListener {
            doListReleaseEdition(query, sortby, nextPage)
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
        ResourcePresenter(this@ListPdfEditionFragment).onListContent()
    }

    private fun doListReleaseEdition(q: String, sortby: String, page: Int) {
        ResourcePresenter(this@ListPdfEditionFragment).onListContent(q, sortby, page.toString())
    }

    override fun handleSuccess(data: BaseResponse) {
        var result = data as ContentResponse
        with(binding) {
            if (result.contents.isNotEmpty()) {
                itemProgressbar.visibility = View.GONE
                itemProgressbarMessage.visibility = View.GONE
                rvList.adapter = EpaperEditionAdapter(result.contents, this@ListPdfEditionFragment)

                nextPage++
            } else {
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
            SubscribeOfferDialog(view, R.style.CoconutDialogFullScreen).show()
        } else {
            App.getNavigationComponent()
                .homeNavigation()
                .navigateToPreview(content!!, view)
        }
    }

}