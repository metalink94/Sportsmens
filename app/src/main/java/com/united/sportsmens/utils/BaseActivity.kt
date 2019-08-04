package com.united.sportsmens.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    open fun getApp(): ForaApp {
        return application as ForaApp
    }
}
