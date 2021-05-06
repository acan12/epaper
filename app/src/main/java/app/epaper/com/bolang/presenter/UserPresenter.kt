package app.epaper.com.bolang.presenter

import android.content.Context
import app.beelabs.com.codebase.base.BasePresenter
import app.beelabs.com.codebase.base.contract.IView
import app.beelabs.com.codebase.support.rx.RxObserver
import app.epaper.com.bolang.model.entity.reponse.ContentResponse
import app.epaper.com.bolang.model.entity.reponse.TransactionResponse
import app.epaper.com.bolang.model.entity.request.TransactionRequest
import app.epaper.com.bolang.model.repository.ResourceRepository
import app.epaper.com.bolang.model.repository.UserRepository

class UserPresenter(var iview: IView) : BasePresenter() {

    private val repository: UserRepository = UserRepository()

    fun onProfile() {
        repository.onCallApiProfile(iview as Context)
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

    fun onSubmit(request: TransactionRequest) {
        repository.onCallApiSubmitTransactionUser(request, iview as Context)
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

    fun onTransactionUser(){
        repository.onCallApiTransactionUser(iview as Context)
            ?.subscribe(
                object: RxObserver<TransactionResponse>(iview){
                    override fun onNext(o: Any) {
                        super.onNext(o)
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                    }
                }.setDialogType(RxObserver.DialogTypeEnum.DEFAULT)
                    .setEnableCoconutAlertConnection(true)
            )
    }
}