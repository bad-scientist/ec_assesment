package com.kevinolivera.ec.di.modules

import com.kevinolivera.ec.App
import com.kevinolivera.ec.data.local.CartDao
import com.kevinolivera.ec.data.local.ProductDao
import com.kevinolivera.ec.data.remote.CartRepository
import com.kevinolivera.ec.data.remote.ProductRepository
import com.kevinolivera.ec.data.remote.api.ProductApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DatabaseModule::class])
class RepositoryModule {
    @Provides
    fun provideProductRepository(productApi: ProductApi, productDao: ProductDao) : ProductRepository =
        ProductRepository(productApi, productDao)

    @Provides
    fun provideCartRepository(cartDao: CartDao) : CartRepository =
        CartRepository(cartDao)
}