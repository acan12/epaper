package app.epaper.com.bolang.presenter

import android.content.Context
import app.beelabs.com.codebase.base.BasePresenter
import app.beelabs.com.codebase.base.contract.IView
import app.beelabs.com.codebase.base.response.BaseResponse
import app.beelabs.com.codebase.support.rx.RxObserver
import app.epaper.com.bolang.model.entity.User
import app.epaper.com.bolang.model.entity.reponse.LoginResponse
import app.epaper.com.bolang.model.entity.reponse.SignupResponse
import app.epaper.com.bolang.model.entity.reponse.TransactionResponse
import app.epaper.com.bolang.model.entity.request.LoginRequest
import app.epaper.com.bolang.model.entity.request.SignupRequest
import app.epaper.com.bolang.model.repository.AuthRepository
import app.epaper.com.bolang.model.repository.ResourceRepository
import app.epaper.com.bolang.ui.impl.IAuthView

class AuthPresenter(val iview: IView) : BasePresenter() {

    private val repository: AuthRepository = AuthRepository()

    fun onLogin(request: LoginRequest){
        repository.onCallApiLogin(request, iview.currentActivity)
            ?.subscribe(
                object: RxObserver<LoginResponse>(iview, "Loading"){
                    override fun onNext(o: Any) {
                        super.onNext(o)
                        (iview as IAuthView).handleSuccess(o as LoginResponse)
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                    }
                }.setDialogType(RxObserver.DialogTypeEnum.DEFAULT)
                    .setEnableCoconutAlertConnection(true)
            )
    }

    fun onSignupUser(request: SignupRequest){
        repository.onCallApiSignup(request, iview.currentActivity)
            ?.subscribe(
                object: RxObserver<SignupResponse>(iview, "Signup"){
                    override fun onNext(o: Any) {
                        super.onNext(o)
                        (iview as IAuthView).handleSuccess(o as BaseResponse)
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                    }
                }.setDialogType(RxObserver.DialogTypeEnum.DEFAULT)
                    .setEnableCoconutAlertConnection(true)
            )
    }
}