package com.united.sportsmens.splash

import com.united.sportsmens.utils.mvp.IView

interface SplashView: IView {
    fun showStub()
    fun showWeb(url: String? = null)
    fun checkCountry()
}
