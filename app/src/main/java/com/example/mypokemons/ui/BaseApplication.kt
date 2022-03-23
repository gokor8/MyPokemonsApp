package com.example.mypokemons.ui

import android.app.Application
import com.example.di.AppComponent
import com.example.di.DaggerAppComponent
import com.example.di.StorageModule

class BaseApplication : Application() {

    lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        mAppComponent = DaggerAppComponent.builder()
            .storageModule(StorageModule(this)).build()
    }

    fun getAppComponent() = mAppComponent
}