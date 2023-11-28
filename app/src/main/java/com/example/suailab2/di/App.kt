package com.example.suailab2.di

import android.app.Application

class App : Application() {
    lateinit var appComponent:AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory()
            .create(context = this)
    }
}