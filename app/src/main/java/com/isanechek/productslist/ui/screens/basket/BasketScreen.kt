package com.isanechek.productslist.ui.screens.basket

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.isanechek.productslist.models.DialogData
import com.isanechek.productslist.models.UiState
import com.isanechek.productslist.ui.screens.basket.components.BasketContent
import com.isanechek.productslist.ui.screens.basket.components.BasketTopBar
import com.isanechek.productslist.ui.screens.main.components.MainDialogAddBasket
import org.koin.androidx.compose.getViewModel

@Composable
fun BasketScreen(vm: BasketViewModel = getViewModel(), popUp: () -> Unit) {
    val uiState = vm.uiState.collectAsState()

    val dialogState = remember {
        mutableStateOf(DialogData.empty())
    }
    MainDialogAddBasket(state = dialogState, addToBasket = {
        vm.updateCount(dialogState.value.productId, dialogState.value.count)
    }, deleteProduct = { productId ->
        vm.removeProduct(productId)
    })
    Scaffold(Modifier.fillMaxSize(), topBar = {
        BasketTopBar(popUp)
    }) { paddingValues ->
        when (val data = uiState.value) {
            is UiState.Done -> BasketContent(data.data, paddingValues) { product ->
                dialogState.value = dialogState.value.copy(
                    isShow = true,
                    title = String.format("%s %s ", product.companyName, product.model),
                    price = product.price,
                    productId = product.id,
                    count = product.count,
                    isUpdate = true
                )
            }
            else -> Unit
        }

    }
}