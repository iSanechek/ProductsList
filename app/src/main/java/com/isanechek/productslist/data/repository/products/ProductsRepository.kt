package com.isanechek.productslist.data.repository.products

import androidx.paging.PagingData
import androidx.sqlite.db.SimpleSQLiteQuery
import com.isanechek.productslist.models.FakeProduct
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    val buyingCount: Flow<Int>
    fun load(): Flow<PagingData<FakeProduct>>
    fun loadSort(request: SimpleSQLiteQuery): Flow<PagingData<FakeProduct>>
    suspend fun loadDetail(productId: String): FakeProduct?
}