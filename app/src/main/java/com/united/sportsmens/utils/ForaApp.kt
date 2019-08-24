package com.united.sportsmens.utils

import androidx.multidex.MultiDexApplication
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging

class ForaApp: MultiDexApplication() {

    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        super.onCreate()
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
    }
}
