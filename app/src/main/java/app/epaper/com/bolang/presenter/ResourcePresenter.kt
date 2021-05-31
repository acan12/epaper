package app.epaper.com.bolang.presenter

import android.widget.Toast
import app.beelabs.com.codebase.base.BasePresenter
import app.beelabs.com.codebase.base.contract.IView
import app.beelabs.com.codebase.support.rx.RxObserver
import app.epaper.com.bolang.model.entity.reponse.ContentResponse
import app.epaper.com.bolang.model.entity.reponse.ProductResponse
import app.epaper.com.bolang.model.repository.ResourceRepository
import app.epaper.com.bolang.ui.impl.IHomeView
import app.epaper.com.bolang.ui.impl.ISubscribeView

class ResourcePresenter(val iview: IView) : BasePresenter() {

    private val repository: ResourceRepository = ResourceRepository()

    fun onListContent(
        query: String = "",
        sortby: String = "asc",
        page: String = "1",
        perPage: String = "10"
    ) {
        var queryMap = HashMap<String, String>()
        queryMap["q"] = query
        queryMap["sortby"] = sortby
        queryMap["page"] = page
        queryMap["per_page"] = perPage
        repository.onCallApiListContent(queryMap, iview.currentActivity)
            ?.subscribe(
                object : RxObserver<ContentResponse>(iview) {

                    override fun onNext(o: Any) {
                        super.onNext(o)
                        (iview as IHomeView).handleSuccess(o as ContentResponse)
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(iview.currentActivity, e.message, Toast.LENGTH_SHORT).show()
                        super.onError(e)
                    }
                }.setDialogType(RxObserver.DialogTypeEnum.SPINKIT)
                    .setEnableCoconutAlertConnection(true)
            )
    }

    fun onProductSubscribe() {
        repository.onCallApiProductSubscribe(iview.currentActivity)
            ?.subscribe(
                object : RxObserver<ProductResponse>(iview) {

                    override fun onNext(o: Any) {
                        super.onNext(o)
                        (iview as ISubscribeView).handleListSubscribeSuccess(o as ProductResponse)
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(iview.currentActivity, e.message, Toast.LENGTH_SHORT).show()
                        super.onError(e)
                    }
                }.setDialogType(RxObserver.DialogTypeEnum.SPINKIT)
                    .setEnableCoconutAlertConnection(true)
            )
    }

}