package com.isanechek.productslist.ui.screens.basket.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.isanechek.productslist.models.BuyingProduct

@Composable
fun BasketContent(data: List<BuyingProduct>, paddingValues: PaddingValues, openDialog: (BuyingProduct) -> Unit) {
    LazyColumn(Modifier.fillMaxSize().padding(paddingValues)) {
        items(data) { item: BuyingProduct ->
            BasketListItem(product = item) { product ->
                openDialog(product)
            }
        }
    }
}