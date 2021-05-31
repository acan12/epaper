package app.epaper.com.bolang.model.api

import android.content.Context
import app.beelabs.com.codebase.base.BaseApi
import app.epaper.com.bolang.App
import app.epaper.com.bolang.BuildConfig
import app.epaper.com.bolang.IConfig
import app.epaper.com.bolang.model.entity.reponse.*
import app.epaper.com.bolang.model.entity.request.LoginRequest
import app.epaper.com.bolang.model.entity.request.SignupRequest
import app.epaper.com.bolang.model.entity.request.TransactionRequest
import app.epaper.com.bolang.presenter.manager.SessionManager
import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.reactivex.Observable


class Api : BaseApi() {

    companion object {
        private fun initHeaderWithToken(context: Context): Map<String, String> {
            val bearerValue = SessionManager.getCredential(context)
            //   CacheUtil.getPreferenceString(IConfig.SESSION_TOKEN_CREDENTIAL, context)
            val map = HashMap<String, String>()
            map["Content-Type"] = "application/json"
            if (bearerValue.isNotEmpty())
                map["Authorization"] = "Bearer $bearerValue"

            return map
        }

        @Synchronized
        private fun initApiDomain(context: Context): ApiService {
            return getInstance().setupApiDomain(
                IConfig.API_BASE_URL,
                App.getAppComponent(),
                ApiService::class.java,
                true,
                app.beelabs.com.codebase.IConfig.TIMEOUT_SHORT_INSECOND,
                BuildConfig.IS_DEBUG,
                arrayOf(ChuckerInterceptor(context)),
                null
            ) as ApiService
        }

        @Synchronized
        fun apiLogin(loginRequest: LoginRequest, context: Context): Observable<LoginResponse?>? {
            return initApiDomain(context).callApiLogin(loginRequest)
        }

        @Synchronized
        fun apiSignup(
            signupRequest: SignupRequest,
            context: Context
        ): Observable<SignupResponse?>? {
            return initApiDomain(context).callApiSignup(signupRequest)
        }

        @Synchronized
        fun apiListContent(queryMap: Map<String, String>, context: Context): Observable<ContentResponse?>? {
            return initApiDomain(context).callApiContentEdition(initHeaderWithToken(context), queryMap)
        }


        @Synchronized
        fun apiSubscribeTransactionUser(
            transactionRequest: TransactionRequest,
            context: Context
        ): Observable<SubscribeResponse?>? {
            return initApiDomain(context).callApiTransaction(
                initHeaderWithToken(context),
                transactionRequest
            )
        }

        @Synchronized
        fun apiListTransactionUser(context: Context): Observable<TransactionResponse?>? {
            return initApiDomain(context).callApiListTransactionUser(initHeaderWithToken(context))
        }

        @Synchronized
        fun apiListProduct(context: Context): Observable<ProductResponse?>? {
            return initApiDomain(context).callApiProduct(initHeaderWithToken(context))
        }

        @Synchronized
        fun apiProfileUser(context: Context): Observable<ProfileResponse?>? {
            return initApiDomain(context).callApiProfileUser(initHeaderWithToken(context))
        }

    }
}