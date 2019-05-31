package com.united.sportsmens.splash

import android.util.Log
import com.united.sportsmens.Features
import com.united.sportsmens.utils.mvp.ViewPresenter

class SplashPresenter: ViewPresenter<SplashView>() {

    var countryISO: String? = null
    var isNeedStub: Boolean = true
    var countryDatabaseIso: String? = Features.COUNTRY_ISO_CODE
    var inCorrectCountry = false
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
            getView()?.showWeb()
        } else {
            getView()?.showStub()
        }
    }

    private fun isCorrectCountry(): Boolean {
        var isCorrect = false
        Log.d("ISO", "get Country ISO $countryISO and $countryDatabaseIso")
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
