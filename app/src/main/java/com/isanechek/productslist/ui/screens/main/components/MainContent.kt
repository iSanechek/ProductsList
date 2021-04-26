package com.isanechek.productslist.ui.screens.main.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.isanechek.productslist.models.FakeProduct
import kotlinx.coroutines.flow.Flow

@Composable
fun MainContent(
    paddingValues: PaddingValues,
    scrollState: LazyListState,
    data: Flow<PagingData<FakeProduct>>,
    openDetail: (String) -> Unit,
    openDialog: (String, String) -> Unit
) {
    val products = data.collectAsLazyPagingItems()
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(paddingValues),
        state = scrollState
    ) {

        items(products) { product ->
            if (product != null) {
                ListProductItem(product, openDetail, openDialog)
            }
        }
    }
}