package com.isanechek.productslist.usecases

import com.isanechek.productslist.data.repository.buying.BuyingRepository
import kotlinx.coroutines.flow.Flow

class LoadPriceUseCase(
    private val buyingRepository: BuyingRepository
) {

    fun loadPrice(productId: String?): Flow<Int> {
        require(productId != null) { "Product id is null!" }
        return buyingRepository.priceCount(productId)
    }
}