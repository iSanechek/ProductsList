package com.isanechek.productslist.data.repository.products.impl

import androidx.paging.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.isanechek.productslist.data.tools.Mapper
import com.isanechek.productslist.data.local.entity.FakeProductEntity
import com.isanechek.productslist.data.remote.fakedata.FakeRemoteData
import com.isanechek.productslist.data.repository.products.ProductsRepository
import com.isanechek.productslist.data.repository.products.paging.ProductsMediator
import com.isanechek.productslist.data.repository.storage.BuyingStorageRepository
import com.isanechek.productslist.data.repository.storage.ProductsStorageRepository
import com.isanechek.productslist.models.FakeProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductsRepositoryImpl(
    private val fakeRemoteData: FakeRemoteData,
    private val productsStorageRepository: ProductsStorageRepository,
    private val mapper: Mapper<FakeProductEntity, FakeProduct>,
    private val buyingStorageRepository: BuyingStorageRepository
) : ProductsRepository {

    override val buyingCount: Flow<Int>
        get() = buyingStorageRepository.countProducts

    @OptIn(ExperimentalPagingApi::class)
    override fun load(): Flow<PagingData<FakeProduct>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 3),
            remoteMediator = ProductsMediator(fakeRemoteData, productsStorageRepository)
        ) {
            productsStorageRepository.loadPaged()
        }.flow.map { items -> items.map { item -> mapper.map(item) } }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun loadSort(request: SimpleSQLiteQuery): Flow<PagingData<FakeProduct>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 3),
            remoteMediator = ProductsMediator(fakeRemoteData, productsStorageRepository)
        ) {
            productsStorageRepository.loadSortPaged(request)
        }.flow.map { items -> items.map { item -> mapper.map(item) } }
    }

    override suspend fun loadDetail(productId: String): FakeProduct? {
        val product = productsStorageRepository.loadProduct(productId)
        return if (product != null) mapper.map(product) else null
    }
}