package com.kevinolivera.ec.di

import com.kevinolivera.ec.activities.CartActivity
import com.kevinolivera.ec.activities.ProductActivity
import com.kevinolivera.ec.activities.ProductsActivity
import com.kevinolivera.ec.di.modules.AppModule
import com.kevinolivera.ec.di.modules.NetworkModule
import com.kevinolivera.ec.di.modules.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, NetworkModule::class])
interface Injector {
    fun inject(productsActivity: ProductsActivity)
    fun inject(productsActivity: ProductActivity)
    fun inject(cartActivity: CartActivity)
}