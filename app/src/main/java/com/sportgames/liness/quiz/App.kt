package com.sportgames.liness.quiz

import android.app.Application
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig

class App : Application()  {
    private val YANDEX_API_KEY = "1c4ee242-edd1-4b2a-b78e-a0c249e0f8ed"

    companion object{
        private lateinit var myApp: App
    }

    override fun onCreate() {
        super.onCreate()
        myApp = this
        val config = YandexMetricaConfig.newConfigBuilder(YANDEX_API_KEY).build()
        YandexMetrica.activate(applicationContext, config)
        YandexMetrica.enableActivityAutoTracking(this)
    }

}