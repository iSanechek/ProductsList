package com.isanechek.productslist.data.repository.storage

import androidx.paging.PagingSource
import androidx.sqlite.db.SimpleSQLiteQuery
import com.isanechek.productslist.data.local.entity.FakeProductEntity
import com.isanechek.productslist.data.remote.dto.FakeProductDTO

interface ProductsStorageRepository {
    suspend fun add(items: List<FakeProductDTO>)
    fun loadPaged(): PagingSource<Int, FakeProductEntity>
    fun loadSortPaged(request: SimpleSQLiteQuery): PagingSource<Int, FakeProductEntity>
    suspend fun loadProduct(productId: String): FakeProductEntity?
    suspend fun refresh(items: List<FakeProductDTO>)
}