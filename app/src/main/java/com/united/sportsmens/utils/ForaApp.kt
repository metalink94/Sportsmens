package com.united.sportsmens.utils

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging

class ForaApp: Application() {

    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        super.onCreate()
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
    }
}
