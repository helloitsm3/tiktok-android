package com.example.movielist

import android.app.Application


val prefs: Pref by lazy {
    App.prefs!!
}

class App: Application()
{
    companion object {
        var prefs: Pref? = null
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        prefs = Pref(applicationContext)
    }
}