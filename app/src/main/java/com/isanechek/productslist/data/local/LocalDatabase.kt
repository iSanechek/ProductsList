package com.isanechek.productslist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.isanechek.productslist.data.local.dao.BuyingProductsDao
import com.isanechek.productslist.data.local.dao.ProductsDao
import com.isanechek.productslist.data.local.entity.BuyingProductEntity
import com.isanechek.productslist.data.local.entity.FakeProductEntity

@Database(
    entities = [(FakeProductEntity::class), (BuyingProductEntity::class)],
    version = 2,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun productsDoa(): ProductsDao
    abstract fun buyingProductsDao(): BuyingProductsDao
}