package com.rostrbuster.rosterbustertest.utils

import android.app.Application
import android.content.Context
import com.google.gson.Gson

class App : Application() {

    val context: Context
        get() = applicationContext

    companion object {
        lateinit var instance: App
    }


    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    var gson = Gson()
}