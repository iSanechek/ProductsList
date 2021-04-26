package com.isanechek.productslist.data.repository.buying

import com.isanechek.productslist.models.BuyingProduct
import kotlinx.coroutines.flow.Flow

interface BuyingRepository {
    suspend fun addToBasket(product: BuyingProduct)
    suspend fun getProduct(productId: String): BuyingProduct?
    suspend fun updateCountProducts(productId: String, count: Int)
    suspend fun remove(productId: String)
    fun loadData(): Flow<List<BuyingProduct>>
    fun priceCount(productId: String): Flow<Int>
}