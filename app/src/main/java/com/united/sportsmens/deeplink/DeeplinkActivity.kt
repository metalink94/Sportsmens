package com.united.sportsmens.deeplink

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.facebook.applinks.AppLinkData
import com.united.sportsmens.splash.SplashActivity
import com.united.sportsmens.utils.BaseActivity

class DeeplinkActivity: BaseActivity() {

    lateinit var appLinkHelper: AppLinkHelper

    override fun onStart() {
        super.onStart()
        setVisible(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appLinkHelper = AppLinkHelper(this, getSharedPreferences(AppLinkHelper.PREFRENCE, Context.MODE_PRIVATE))
        AppLinkData.fetchDeferredAppLinkData(this) { appLinkData: AppLinkData? ->
            try {
                appLinkData?.let { appLinkHelper.create(it.targetUri) }
            } catch (e: Exception) {
                Log.e("AppLinkException", e.localizedMessage)
            }
            try {
                appLinkHelper.createBoltsAppLink()
            } catch (e: Exception) {
                Log.e("AppLinkException", e.localizedMessage)
            }
            runOnUiThread {
                showWebView()
            }
        }
    }

    private fun showWebView() {
        startActivity(Intent(this, SplashActivity::class.java))
        finish()
    }
}
