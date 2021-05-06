package app.epaper.com.bolang.model.repository

import android.content.Context
import app.beelabs.com.codebase.base.BaseDao
import app.epaper.com.bolang.model.api.Api
import app.epaper.com.bolang.model.entity.reponse.LoginResponse
import app.epaper.com.bolang.model.entity.reponse.SignupResponse
import app.epaper.com.bolang.model.entity.request.LoginRequest
import app.epaper.com.bolang.model.entity.request.SignupRequest
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthRepository : BaseDao() {
    fun onCallApiLogin(
        req: LoginRequest,
        context: Context
    ): Observable<LoginResponse?>? {
        return Api.apiLogin(req, context)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

    fun onCallApiSignup(
        req: SignupRequest,
        context: Context
    ): Observable<SignupResponse?>? {
        return Api.apiSignup(req, context)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }
}