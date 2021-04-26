package com.isanechek.productslist.ui.screens.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isanechek.productslist.models.BuyingProduct
import com.isanechek.productslist.models.UiState
import com.isanechek.productslist.usecases.LoadBuyingDataUseCase
import com.isanechek.productslist.usecases.RemoveBuyingProductUseCase
import com.isanechek.productslist.usecases.UpdateBuyingCountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BasketViewModel(
    private val loadBuyingDataUseCase: LoadBuyingDataUseCase,
    private val updateBuyingCountUseCase: UpdateBuyingCountUseCase,
    private val removeBuyingProductUseCase: RemoveBuyingProductUseCase
) : ViewModel() {

    private val stateUi = MutableStateFlow<UiState<List<BuyingProduct>>>(UiState.Empty)
    val uiState: StateFlow<UiState<List<BuyingProduct>>> get() = stateUi

    init {
        viewModelScope.launch {
            loadBuyingDataUseCase.load().collect { data ->
                setUiState(UiState.Done(data))
            }
        }
    }

    fun updateCount(productId: String, count: Int) {
        viewModelScope.launch {
            updateBuyingCountUseCase.update(productId, count)
        }
    }

    fun removeProduct(productId: String) {
        viewModelScope.launch {
            removeBuyingProductUseCase.remove(productId)
        }
    }

    private fun setUiState(state: UiState<List<BuyingProduct>>) {
        stateUi.value = state
    }
}