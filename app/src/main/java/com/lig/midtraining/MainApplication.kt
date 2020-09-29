package com.lig.midtraining

import android.app.Application
import com.lig.midtraining.data.remote.SingletonRequest
import com.lig.midtraining.di.AppComponent
import com.lig.midtraining.di.AppModule
import com.lig.midtraining.di.DaggerAppComponent

class MainApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
//        SingletonRequest.init(this)
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
//        component = DaggerAppComponent.builder().build()

    }

}