package com.sportgames.liness.quiz

import android.app.Application
import com.onesignal.OneSignal

class App : Application()  {

    private val ONESIGNAL_APP_ID = "452d36d2-d92b-4635-9944-e5fac2159c44"

    companion object{
        private lateinit var myApp: App

        @Synchronized
        fun getInstance() = myApp
    }

    override fun onCreate() {
        super.onCreate()
        initOneConfig()
        myApp = this
    }

    private fun initOneConfig() {
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this)

        OneSignal.unsubscribeWhenNotificationsAreDisabled(true)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}