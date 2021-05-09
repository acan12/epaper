package app.epaper.com.bolang.presenter

import android.content.Context
import android.widget.Toast
import app.beelabs.com.codebase.base.BasePresenter
import app.beelabs.com.codebase.base.contract.IView
import app.beelabs.com.codebase.support.rx.RxObserver
import app.epaper.com.bolang.R
import app.epaper.com.bolang.model.entity.reponse.ProfileResponse
import app.epaper.com.bolang.model.entity.reponse.SubscribeResponse
import app.epaper.com.bolang.model.entity.reponse.TransactionResponse
import app.epaper.com.bolang.model.entity.request.TransactionRequest
import app.epaper.com.bolang.model.repository.UserRepository
import app.epaper.com.bolang.ui.impl.ISubscribeView
import app.epaper.com.bolang.ui.impl.IProfileView

class UserPresenter(var iview: IView) : BasePresenter() {

    private val repository: UserRepository = UserRepository()

    fun onProfile() {
        repository.onCallApiProfile(iview.currentActivity)
            ?.subscribe(
                object : RxObserver<ProfileResponse>(
                    iview,
                    iview.currentActivity.getString(R.string.update_data_loading)
                ) {

                    override fun onNext(o: Any) {
                        super.onNext(o)
                        (iview as IProfileView).handleProfileSuccess(o as ProfileResponse)
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(iview.currentActivity, e.message, Toast.LENGTH_SHORT).show()
                        super.onError(e)
                    }
                }.setDialogType(RxObserver.DialogTypeEnum.DEFAULT)
                    .setEnableCoconutAlertConnection(true)
            )
    }

    fun onSubmit(request: TransactionRequest) {
        repository.onCallApiSubmitTransactionUser(request, iview.currentActivity)
            ?.subscribe(
                object : RxObserver<SubscribeResponse>(iview, "Processing") {

                    override fun onNext(o: Any) {
                        super.onNext(o)
                        (iview as ISubscribeView).handleSubscribeSuccess(o as SubscribeResponse)
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(iview.currentActivity, e.message, Toast.LENGTH_SHORT).show()
                        super.onError(e)
                    }
                }.setDialogType(RxObserver.DialogTypeEnum.DEFAULT)
                    .setEnableCoconutAlertConnection(true)
            )
    }

    fun onTransactionUser() {
        repository.onCallApiTransactionUser(iview as Context)
            ?.subscribe(
                object : RxObserver<TransactionResponse>(iview) {
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