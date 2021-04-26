package com.isanechek.productslist.usecases

import com.isanechek.productslist.data.repository.buying.BuyingRepository

class RemoveBuyingProductUseCase(private val buyingRepository: BuyingRepository) {

    suspend fun remove(productId: String) {
        buyingRepository.remove(productId)
    }
}