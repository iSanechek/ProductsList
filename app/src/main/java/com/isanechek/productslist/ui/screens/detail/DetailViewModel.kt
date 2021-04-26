package com.isanechek.productslist.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isanechek.productslist.models.FakeProduct
import com.isanechek.productslist.models.PriceProduct
import com.isanechek.productslist.models.UiState
import com.isanechek.productslist.usecases.AddToShoppingCartUseCase
import com.isanechek.productslist.usecases.LoadPriceUseCase
import com.isanechek.productslist.usecases.LoadProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(
    private val loadProductUseCase: LoadProductUseCase,
    private val addToShoppingCartUseCase: AddToShoppingCartUseCase,
    private val loadPriceUseCase: LoadPriceUseCase
) : ViewModel() {

    private val state = MutableStateFlow<UiState<FakeProduct>>(UiState.Empty)
    val uiState: StateFlow<UiState<FakeProduct>> = state

    private val priceState = MutableStateFlow(PriceProduct.empty())
    val uiPriceState: StateFlow<PriceProduct> = priceState

    fun loadProduct(productId: String?) {
        viewModelScope.launch {
            state.value = loadProductUseCase.loadProduct(productId)
            loadPriceUseCase.loadPrice(productId).collect { value ->
                if (value != 0) {
                    setPriceState(
                        value
                    )
                }
            }
        }
    }

    private val addState = MutableStateFlow<UiState<String>>(UiState.Empty)
    val addUiState: StateFlow<UiState<String>> = addState

    fun addBasket(productId: String?, count: Int) {
        viewModelScope.launch {
            addToShoppingCartUseCase.add(productId, count).collect { stateUi ->
                addUiState(stateUi)
            }
        }
    }

    private fun setPriceState(value: Int) {
        priceState.value = PriceProduct(
            price = 0,
            productCount = value,
            totalPrice = 0
        )
    }

    private fun addUiState(state: UiState<String>) {
        addState.value = state
    }
}