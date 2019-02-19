package com.kevinolivera.ec.di.modules

import com.kevinolivera.ec.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApp(): App = app
}