package com.isanechek.productslist.usecases

import com.isanechek.productslist.data.tools.Mapper
import com.isanechek.productslist.data.repository.buying.BuyingRepository
import com.isanechek.productslist.data.repository.products.ProductsRepository
import com.isanechek.productslist.models.BuyingProduct
import com.isanechek.productslist.models.FakeProduct
import com.isanechek.productslist.models.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddToShoppingCartUseCase(
    private val productsRepository: ProductsRepository,
    private val buyingRepository: BuyingRepository,
    private val mapper: Mapper<FakeProduct, BuyingProduct>
) {

    suspend fun add(productId: String?, count: Int): Flow<UiState<String>> = flow {

        if (productId != null) {
            emit(UiState.Progress(null))
            val product = productsRepository.loadDetail(productId)
            if (product != null) {
                val buyingProduct = buyingRepository.getProduct(productId)
                if (buyingProduct != null) {
                    buyingRepository.updateCountProducts(productId, count)
                } else {
                    buyingRepository.addToBasket(mapper.map(product).copy(count = count))
                }
                emit(UiState.Done("Product ${product.companyName} ${product.model} add basket"))
            } else {
                emit(UiState.Fail("Product not find"))
            }
        } else {
            emit(UiState.Fail("Product id is null!"))
        }

    }
}