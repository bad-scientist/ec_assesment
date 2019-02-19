package com.kevinolivera.ec.di.modules

import com.kevinolivera.ec.data.local.CartDao
import com.kevinolivera.ec.data.remote.CartRepository
import com.kevinolivera.ec.data.remote.PaymentRepository
import com.kevinolivera.ec.data.remote.ProductRepository
import com.kevinolivera.ec.data.remote.api.PaymentApi
import com.kevinolivera.ec.data.remote.api.ProductApi
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, DatabaseModule::class])
class RepositoryModule {
    @Provides
    fun provideProductRepository(productApi: ProductApi) : ProductRepository =
        ProductRepository(productApi)

    @Provides
    fun provideCartRepository(cartDao: CartDao) : CartRepository =
        CartRepository(cartDao)

    @Provides
    fun providePaymentRepository(paymentApi: PaymentApi) : PaymentRepository =
        PaymentRepository(paymentApi)
}