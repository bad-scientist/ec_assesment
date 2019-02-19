package com.kevinolivera.ec

import android.app.Application
import com.kevinolivera.ec.di.DaggerInjector
import com.kevinolivera.ec.di.Injector
import com.kevinolivera.ec.di.modules.AppModule

class App : Application() {

    lateinit var injector: Injector

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger(){
        injector = DaggerInjector
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}