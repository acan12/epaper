package app.epaper.com.bolang.presenter.manager

import android.content.Context
import app.beelabs.com.codebase.support.util.CacheUtil
import app.epaper.com.bolang.IConfig
import app.epaper.com.bolang.IConfig.Companion.STATUS_CANCEL
import app.epaper.com.bolang.IConfig.Companion.STATUS_CANCEL_TEXT
import app.epaper.com.bolang.IConfig.Companion.STATUS_DONE
import app.epaper.com.bolang.IConfig.Companion.STATUS_DONE_TEXT
import app.epaper.com.bolang.IConfig.Companion.STATUS_PENDING
import app.epaper.com.bolang.IConfig.Companion.STATUS_PENDING_TEXT

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

    fun setSubscribeStatus(status: String, context: Context) {
        CacheUtil.putPreferenceString(IConfig.SESSION_STATUS_SUBS_KEY, status, context)!!
    }

    fun getSubscribeStatus(context: Context): Int {
        var status = CacheUtil.getPreferenceString(IConfig.SESSION_STATUS_SUBS_KEY, context)!!
        return when(status){
            STATUS_PENDING_TEXT -> STATUS_PENDING
            STATUS_DONE_TEXT -> STATUS_DONE
            STATUS_CANCEL_TEXT -> STATUS_CANCEL
            else -> 0
        }
    }



    fun setSkip(setSkip: Boolean = false, context: Context) {
        CacheUtil.putPreferenceBoolean(IConfig.SESSION_SKIP_KEY, setSkip, context)!!
    }
}
