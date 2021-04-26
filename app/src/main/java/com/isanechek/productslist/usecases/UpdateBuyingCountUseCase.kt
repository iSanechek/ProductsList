package com.isanechek.productslist.usecases

import com.isanechek.productslist.data.repository.buying.BuyingRepository

class UpdateBuyingCountUseCase(private val buyingRepository: BuyingRepository) {

    suspend fun update(productId: String?, count: Int) {
        require(productId != null) { "Product id is null!" }
        buyingRepository.updateCountProducts(productId, count)
    }
}