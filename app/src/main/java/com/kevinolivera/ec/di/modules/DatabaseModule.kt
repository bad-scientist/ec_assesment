package com.kevinolivera.ec.di.modules

import androidx.room.Room
import com.kevinolivera.ec.App
import com.kevinolivera.ec.data.local.AppDatabase
import com.kevinolivera.ec.data.local.CartDao
import com.kevinolivera.ec.data.local.ProductDao
import com.kevinolivera.ec.data.remote.ProductRepository
import com.kevinolivera.ec.data.remote.api.ProductApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: App): AppDatabase {
        return Room.databaseBuilder(
            app, AppDatabase::class.java,
            "ec-shop-database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideCartDao(appDatabase: AppDatabase): CartDao {
        return appDatabase.getCartDao()
    }

    @Provides
    @Singleton
    fun provideProductDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.getProductDao()
    }
}
