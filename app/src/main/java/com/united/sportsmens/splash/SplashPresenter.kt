package com.united.sportsmens.splash

import com.united.sportsmens.Features
import com.united.sportsmens.deeplink.AppLinkHelper
import com.united.sportsmens.utils.mvp.ViewPresenter

class SplashPresenter: ViewPresenter<SplashView>() {

    var countryISO: String? = null
    var isNeedStub: Boolean = true
    var countryDatabaseIso: String? = Features.COUNTRY_ISO_CODE
    var inCorrectCountry = false
    lateinit var appLinkHelper: AppLinkHelper

    private var list: MutableList<String> = mutableListOf()

    fun checkIp() {
        stringToList()
        inCorrectCountry = isCorrectCountry()
        if (inCorrectCountry) {
            showNeededScreen()
        } else {
            getView()?.showStub()
        }
    }

    private fun stringToList() {
        val countries = countryDatabaseIso ?: ""
        list = countries.split(",").map { it.trim() }.toMutableList()
    }

    private fun showNeededScreen() {
        if (!isNeedStub && inCorrectCountry) {
            getView()?.showWeb(getAppLinkUrl())
        } else {
            getView()?.showStub()
        }
    }

    private fun getAppLinkUrl(): String? {
        return if (appLinkHelper.isFromDeepLink()) {
            appLinkHelper.getAppLinkUrl()
        } else {
            null
        }
    }

    private fun isCorrectCountry(): Boolean {
        var isCorrect = false
        val countryIso = countryISO ?: ""
        for (country in list) {
            if (country.toLowerCase() == countryIso.toLowerCase()) {
                isCorrect = true
                break
            }
        }
        return isCorrect
    }

    fun onCreate() {
        getView()?.checkCountry()
    }
}
