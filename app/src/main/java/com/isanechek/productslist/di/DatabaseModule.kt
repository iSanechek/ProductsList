package com.isanechek.productslist.di

import androidx.room.Room
import com.isanechek.productslist.data.local.LocalDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext().applicationContext,
            LocalDatabase::class.java,
            "local.db"
        ).fallbackToDestructiveMigration().build()
    }

    single {
        get<LocalDatabase>().productsDoa()
    }

    factory {
        get<LocalDatabase>().buyingProductsDao()
    }
}