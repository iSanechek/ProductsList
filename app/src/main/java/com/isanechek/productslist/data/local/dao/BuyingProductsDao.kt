package com.isanechek.productslist.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.isanechek.productslist.data.local.entity.BuyingProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BuyingProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BuyingProductEntity)

    @Query("SELECT * FROM buying_products WHERE id =:productId")
    suspend fun loadProduct(productId: String): BuyingProductEntity?

    @Query("SELECT * FROM buying_products")
    fun load(): Flow<List<BuyingProductEntity>>

    @Query("UPDATE buying_products SET count =:count WHERE id =:productId")
    suspend fun updateCount(productId: String, count: Int)

    @Query("SELECT count FROM buying_products WHERE id =:productId")
    fun priceCount(productId: String): Flow<Int>

    @Query("SELECT COUNT(*) FROM buying_products")
    fun count(): Flow<Int>

    @Query("DELETE FROM buying_products WHERE id =:productId")
    suspend fun remove(productId: String)
}