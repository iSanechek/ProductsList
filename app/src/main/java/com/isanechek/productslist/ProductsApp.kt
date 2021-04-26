package com.isanechek.productslist

import android.app.Application
import com.isanechek.productslist.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ProductsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ProductsApp)
            modules(appModule, dataModule, databaseModule, useCasesModule, mappersModule)
        }
    }
}