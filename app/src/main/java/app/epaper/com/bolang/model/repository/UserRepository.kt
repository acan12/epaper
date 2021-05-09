package app.epaper.com.bolang.model.repository

import android.content.Context
import app.beelabs.com.codebase.base.BaseDao
import app.epaper.com.bolang.model.api.Api
import app.epaper.com.bolang.model.entity.reponse.*
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
    ): Observable<SubscribeResponse?>? {
        return Api.apiSubscribeTransactionUser(transactionRequest, context)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

}