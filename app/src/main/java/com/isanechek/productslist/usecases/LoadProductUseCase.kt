package com.isanechek.productslist.usecases

import com.isanechek.productslist.data.repository.products.ProductsRepository
import com.isanechek.productslist.models.FakeProduct
import com.isanechek.productslist.models.UiState

class LoadProductUseCase(private val repository: ProductsRepository) {

    suspend fun loadProduct(productId: String?): UiState<FakeProduct> {
        return if (productId != null) {
            val obj = repository.loadDetail(productId)
            return if (obj != null) {
                UiState.Done(obj)
            } else {
                UiState.Fail("Product not find!")
            }
        } else {
            UiState.Fail("Product id is null!")
        }
    }
}