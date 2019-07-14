package com.united.sportsmens.utils

import androidx.multidex.MultiDexApplication
import com.facebook.FacebookSdk
import com.facebook.LoggingBehavior
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging

class ForaApp: MultiDexApplication() {

    lateinit var logger: AppEventsLogger

    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        super.onCreate()
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS)
        logger = AppEventsLogger.newLogger(this)
    }
}
