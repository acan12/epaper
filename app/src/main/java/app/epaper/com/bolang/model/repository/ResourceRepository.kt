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

class ResourceRepository : BaseDao() {
    fun onCallApiListContent(queryMap: Map<String, String>,
        context: Context
    ): Observable<ContentResponse?>? {
        return Api.apiListContent(queryMap, context)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

    fun onCallApiProductSubscribe(
        context: Context
    ): Observable<ProductResponse?>? {
        return Api.apiListProduct(context)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }



}