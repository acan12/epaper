package app.epaper.com.bolang.model.repository

import android.content.Context
import app.beelabs.com.codebase.base.BaseDao
import app.epaper.com.bolang.model.api.Api
import app.epaper.com.bolang.model.entity.reponse.LoginResponse
import app.epaper.com.bolang.model.entity.reponse.ProfileResponse
import app.epaper.com.bolang.model.entity.reponse.SignupResponse
import app.epaper.com.bolang.model.entity.reponse.TransactionResponse
import app.epaper.com.bolang.model.entity.request.LoginRequest
import app.epaper.com.bolang.model.entity.request.SignupRequest
import app.epaper.com.bolang.model.entity.request.TransactionRequest
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository : BaseDao() {
    fun onCallApiProfile(
        context: Context
    ): Observable<ProfileResponse?>? {
        return Api.apiProfileUser(context)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

    fun onCallApiTransactionUser(
        context: Context
    ): Observable<TransactionResponse?>? {
        return Api.apiListTransactionUser(context)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

    fun onCallApiSubmitTransactionUser(
        transactionRequest: TransactionRequest,
        context: Context
    ): Observable<TransactionResponse?>? {
        return Api.apiSubscribeTransactionUser(transactionRequest, context)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

}