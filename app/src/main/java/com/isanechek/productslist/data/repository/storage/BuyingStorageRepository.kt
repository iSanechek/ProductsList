package com.isanechek.productslist.data.repository.storage

import com.isanechek.productslist.models.BuyingProduct
import kotlinx.coroutines.flow.Flow

interface BuyingStorageRepository {
    val countProducts: Flow<Int>
    suspend fun add(product: BuyingProduct)
    suspend fun get(productId: String): BuyingProduct?
    suspend fun updateCount(productId: String, count: Int)
    suspend fun removeProduct(productId: String)
    fun load(): Flow<List<BuyingProduct>>
    fun priceCount(productId: String): Flow<Int>
}