package app.epaper.com.bolang.presenter.manager

import android.content.Context
import app.beelabs.com.codebase.support.util.CacheUtil
import app.epaper.com.bolang.IConfig

object SessionManager {

    fun isLogin(context: Context): Boolean = getCredential(context).isNotEmpty()
    fun isSkip(context: Context): Boolean =
        CacheUtil.getPreferenceBoolean(IConfig.SESSION_SKIP_KEY, context)!!

    fun putCredential(token: String, context: Context) {
        CacheUtil.putPreferenceString(IConfig.SESSION_TOKEN_CREDENTIAL, token, context)
    }

    fun putPersonaFirstName(name: String, context: Context) {
        CacheUtil.putPreferenceString(IConfig.SESSION_PERSONA_FIRSTNAME, name, context)
    }

    fun getPersonaFirstName(context: Context): String =
        CacheUtil.getPreferenceString(IConfig.SESSION_PERSONA_FIRSTNAME, context)


    fun clearSessionLogin(context: Context) {
        CacheUtil.putPreferenceString(IConfig.SESSION_TOKEN_CREDENTIAL, "", context)
    }

    fun getCredential(context: Context): String {
        val token = CacheUtil.getPreferenceString(IConfig.SESSION_TOKEN_CREDENTIAL, context)
        return token ?: ""
    }


    fun isSubscribe(context: Context): Boolean {
        return CacheUtil.getPreferenceBoolean(IConfig.SESSION_SUBS_KEY, context)!!
    }

    fun setSubscribe(setSubscribe: Boolean = false, context: Context) {
        CacheUtil.putPreferenceBoolean(IConfig.SESSION_SUBS_KEY, setSubscribe, context)!!
    }

    fun setSkip(setSkip: Boolean = false, context: Context) {
        CacheUtil.putPreferenceBoolean(IConfig.SESSION_SKIP_KEY, setSkip, context)!!
    }
}
