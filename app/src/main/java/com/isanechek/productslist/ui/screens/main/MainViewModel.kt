package com.isanechek.productslist.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.isanechek.productslist.models.FakeProduct
import com.isanechek.productslist.models.SortingState
import com.isanechek.productslist.models.UiState
import com.isanechek.productslist.usecases.AddToShoppingCartUseCase
import com.isanechek.productslist.usecases.LoadDataUseCase
import com.isanechek.productslist.utils.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val loadDataUseCase: LoadDataUseCase,
    private val addToShoppingCartUseCase: AddToShoppingCartUseCase
) : ViewModel() {

    /**
     * Получаем количество добавленных в корзину,
     * чтобы показать в тулбаре
     */
    val buyingProducts: Flow<Int> get() = loadDataUseCase.buyingProducts

    /**
     * Получаем список продуктов
     */
    fun loadProducts(sortingState: SortingState): Flow<PagingData<FakeProduct>> =
        loadDataUseCase.loadSortData(viewModelScope, sortingState)

    /**
     * Для добавления товара в корзину
     */
    fun addToShoppingCart(productId: String, count: Int) {
        viewModelScope.launch {
            addToShoppingCartUseCase.add(productId, count).collect { state ->
                when (state) {
                    is UiState.Done -> {
                        log { "ADD DONE" }
                    }
                    is UiState.Progress -> {
                        log { "PROGRESS DONE" }
                    }
                    is UiState.Empty -> {
                        log { "EMPTY DONE" }
                    }
                    is UiState.Fail -> {
                        log { "FAIL DONE" }
                    }
                }
            }
        }
    }
}