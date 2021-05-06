package app.epaper.com.bolang.presenter

import android.content.Context
import app.beelabs.com.codebase.base.BasePresenter
import app.beelabs.com.codebase.base.contract.IView
import app.beelabs.com.codebase.support.rx.RxObserver
import app.epaper.com.bolang.model.entity.reponse.ContentResponse
import app.epaper.com.bolang.model.entity.reponse.ProductResponse
import app.epaper.com.bolang.model.repository.ResourceRepository

class ResourcePresenter(val iview: IView) : BasePresenter() {

    private val repository: ResourceRepository = ResourceRepository()

    fun onListContent() {
        repository.onCallApiListContent(iview as Context)
            ?.subscribe(
                object : RxObserver<ContentResponse>(iview) {

                    override fun onNext(o: Any) {
                        super.onNext(o)
//                        (iview as IView).handleDataSuccess(o as ProgramResponse)
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
//                        (iview.currentActivity as MainActivity).handleUserUnauthorized(e as HttpException)
                    }
                }.setDialogType(RxObserver.DialogTypeEnum.SPINKIT)
                    .setEnableCoconutAlertConnection(true)
            )
    }

    fun onProductSubscribe() {
        repository.onCallApiProductSubscribe(iview as Context)
            ?.subscribe(
                object : RxObserver<ProductResponse>(iview) {

                    override fun onNext(o: Any) {
                        super.onNext(o)
//                        (iview as IView).handleDataSuccess(o as ProgramResponse)
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
//                        (iview.currentActivity as MainActivity).handleUserUnauthorized(e as HttpException)
                    }
                }.setDialogType(RxObserver.DialogTypeEnum.SPINKIT)
                    .setEnableCoconutAlertConnection(true)
            )
    }

}