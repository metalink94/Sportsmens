package com.united.sportsmens.deeplink

import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import bolts.AppLinks
import com.united.sportsmens.R
import com.united.sportsmens.utils.BaseActivity

class AppLinkHelper(private val context: BaseActivity,
                    private val sharedPreferences: SharedPreferences) {

    fun create(targetUri: Uri) {
        //add key Id
        targetUri.getQueryParameter(context.getString(R.string.id))?.let {
            addToPrefs(getString(R.string.id), it)
        }

        //add key S1
        targetUri.getQueryParameter(context.getString(R.string.s1))?.let {
            addToPrefs(getString(R.string.s1), it)
        }

        //add key S2
        targetUri.getQueryParameter(context.getString(R.string.s2))?.let {
            addToPrefs(getString(R.string.s2), it)
        }
    }

    private fun addToPrefs(key: String, parameter: String) {
        sharedPreferences.edit().putString(key, parameter).apply()
    }

    private fun getString(stringRes: Int): String = context.getString(stringRes)

    fun createBoltsAppLink() {
        val appLinks = AppLinks.getTargetUrlFromInboundIntent(context, context.intent)
        if (checkAppLinks(getString(R.string.id), appLinks)) {
            addToPrefs(getString(R.string.id), appLinks.getQueryParameter(getString(R.string.id)))
        }

        if (checkAppLinks(getString(R.string.s1), appLinks)) {
            addToPrefs(getString(R.string.s1), appLinks.getQueryParameter(getString(R.string.s1)))
        }

        if (checkAppLinks(getString(R.string.s2), appLinks)) {
            addToPrefs(getString(R.string.s2), appLinks.getQueryParameter(getString(R.string.s2)))
        }
    }

    private fun checkAppLinks(key: String, appLinks: Uri): Boolean = sharedPreferences.getString(key, "").isNullOrEmpty() &&
            !appLinks.getQueryParameter(key).isNullOrEmpty()

    fun getAppLinkUrl(): String {
        return "https://bet4ra.com/go.php?id=${sharedPreferences.getString(getString(R.string.id), "")}&s1=${sharedPreferences.getString(getString(R.string.s1), "")}" +
                "&s2=${sharedPreferences.getString(getString(R.string.s2), "")}"
    }

    fun isFromDeepLink(): Boolean {
        return !sharedPreferences.getString(getString(R.string.id), "").isNullOrEmpty()
    }

    companion object {
        const val PREFRENCE = "deeplink"
    }
}
