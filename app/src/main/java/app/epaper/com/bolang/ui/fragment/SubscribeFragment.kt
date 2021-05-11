package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import app.beelabs.com.codebase.base.BaseFragment
import app.beelabs.com.codebase.base.response.BaseResponse
import app.beelabs.com.codebase.support.util.CacheUtil
import app.epaper.com.bolang.App
import app.epaper.com.bolang.IConfig
import app.epaper.com.bolang.R
import app.epaper.com.bolang.databinding.FragmentSubscribeBinding
import app.epaper.com.bolang.model.entity.reponse.ProductResponse
import app.epaper.com.bolang.model.entity.reponse.SubscribeResponse
import app.epaper.com.bolang.model.entity.request.TransactionRequest
import app.epaper.com.bolang.presenter.ResourcePresenter
import app.epaper.com.bolang.presenter.UserPresenter
import app.epaper.com.bolang.ui.adapter.PaketCardAdapter
import app.epaper.com.bolang.ui.dialog.SubscribeProcessingDialog
import app.epaper.com.bolang.ui.impl.ISubscribeView
import app.epaper.com.bolang.ui.tool.CoreUtil
import kotlinx.android.synthetic.main.fragment_home.*

class SubscribeFragment : BaseFragment(), ISubscribeView {
    private lateinit var binding: FragmentSubscribeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubscribeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {
        rvGridPackage.layoutManager = GridLayoutManager(requireContext(), 2)
        ResourcePresenter(this@SubscribeFragment).onProductSubscribe()
        btnBack.setOnClickListener {
            App.getNavigationComponent().homeNavigation()
                .navigateFromSubscribeToHome(root)
        }
        btnNext.setOnClickListener {
            var jsonString = CacheUtil.getPreferenceString(IConfig.KEY_SUBSCRIBE_PRODUCT_SELECTED, currentActivity)
            var request = CoreUtil
                .getObjectFromJsonStringCache(jsonString, TransactionRequest::class.java) as TransactionRequest
            UserPresenter(this@SubscribeFragment).onSubmit(request)
        }
    }

    override fun handleListSubscribeSuccess(data: BaseResponse) {
        var products = (data as ProductResponse).data
        binding.rvGridPackage.adapter =
            PaketCardAdapter(products, this, this@SubscribeFragment.resources)
    }

    override fun handleSubscribeSuccess(data: BaseResponse) {
        var resp = data as SubscribeResponse
        if(resp.data != null)
            SubscribeProcessingDialog(binding.root, R.style.CoconutDialogFullScreen).show()
    }

    override fun handleSelectedProduct() {
        binding.btnNext.isEnabled =true
    }
}