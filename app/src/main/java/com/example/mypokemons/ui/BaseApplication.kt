package com.example.mypokemons.ui

import android.app.Application
import com.example.mypokemons.data.di.AppComponent
import com.example.mypokemons.data.di.DaggerAppComponent
import com.example.mypokemons.data.di.StorageModule

class BaseApplication : Application() {

    lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        mAppComponent = DaggerAppComponent.builder()
            .storageModule(StorageModule(this)).build()
    }

    fun getAppComponent() = mAppComponent
}