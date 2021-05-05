package app.epaper.com.bolang.model.api

import android.content.Context
import app.epaper.com.bolang.App
import app.epaper.com.bolang.IConfig
import app.epaper.com.bolang.presenter.manager.SessionManager
import app.beelabs.com.codebase.base.BaseApi
import app.epaper.com.bolang.BuildConfig
import com.chuckerteam.chucker.api.ChuckerInterceptor


class Api : BaseApi() {

    companion object {
        private fun initHeaderWithToken(context: Context): Map<String, String> {
            val bearerValue = SessionManager.getCredential(context)
            //   CacheUtil.getPreferenceString(IConfig.SESSION_TOKEN_CREDENTIAL, context)
            val map = HashMap<String, String>()
            map["Content-Type"] = "application/json"
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

    }
}