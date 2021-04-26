package com.isanechek.productslist.data.repository.buying.impl

import com.isanechek.productslist.data.repository.buying.BuyingRepository
import com.isanechek.productslist.data.repository.storage.BuyingStorageRepository
import com.isanechek.productslist.data.repository.storage.ProductsStorageRepository
import com.isanechek.productslist.models.BuyingProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BuyingRepositoryImpl(
    private val productsStorageRepository: ProductsStorageRepository,
    private val buyingStorageRepository: BuyingStorageRepository
) : BuyingRepository {

    override suspend fun addToBasket(product: BuyingProduct) {
        buyingStorageRepository.add(product)
    }

    override fun loadData(): Flow<List<BuyingProduct>> {
        return buyingStorageRepository.load().map { items ->
            items.map { item ->
                val product = productsStorageRepository.loadProduct(item.id)
                item.copy(
                    model = product!!.model,
                    coverUrl = product.coverUrl,
                    companyName = product.companyName,
                    category = product.category
                )
            }
        }
    }

    override suspend fun getProduct(productId: String): BuyingProduct? {
        val product = productsStorageRepository.loadProduct(productId)
        if (product != null) {
            val buyingProduct = buyingStorageRepository.get(productId)
            if (buyingProduct != null) {
                return buyingProduct.copy(
                    model = product.model,
                    category = product.model,
                    companyName = product.companyName,
                    coverUrl = product.coverUrl
                )
            }
        } else return null
        return null
    }

    override suspend fun updateCountProducts(productId: String, count: Int) {
        buyingStorageRepository.updateCount(productId, count)
    }

    override suspend fun remove(productId: String) {
        buyingStorageRepository.removeProduct(productId)
    }

    override fun priceCount(productId: String): Flow<Int> {
        return buyingStorageRepository.priceCount(productId)
    }
}