package com.example.dagger2

import android.app.Application
import com.example.dagger2.di.component.AppComponent
import com.example.dagger2.di.component.DaggerAppComponent

class App: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }
}