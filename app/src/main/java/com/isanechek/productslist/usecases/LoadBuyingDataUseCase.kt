package com.isanechek.productslist.usecases

import com.isanechek.productslist.data.repository.buying.BuyingRepository
import com.isanechek.productslist.models.BuyingProduct
import com.isanechek.productslist.models.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoadBuyingDataUseCase(private val buyingRepository: BuyingRepository) {

    fun load(): Flow<List<BuyingProduct>> = buyingRepository.loadData()
}