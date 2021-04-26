package com.isanechek.productslist.data.repository.products.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.isanechek.productslist.data.local.entity.FakeProductEntity
import com.isanechek.productslist.data.remote.fakedata.FakeRemoteData
import com.isanechek.productslist.data.remote.dto.FakeProductDTO
import com.isanechek.productslist.data.repository.storage.ProductsStorageRepository


@OptIn(ExperimentalPagingApi::class)
class ProductsMediator(
    private val fakeRemoteData: FakeRemoteData,
    private val productsStorageRepository: ProductsStorageRepository
) : RemoteMediator<Int, FakeProductEntity>() {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, FakeProductEntity>
    ): MediatorResult {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteData = loadFakeData(loadType, state)
                return if (remoteData.isNotEmpty()) {
                    productsStorageRepository.add(remoteData)
                    MediatorResult.Success(endOfPaginationReached = true)
                } else MediatorResult.Error(Exception("Load data error!"))
            }
            LoadType.PREPEND -> {
                MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> {
                val remoteData = loadFakeData(loadType, state)
                return if (remoteData.isNotEmpty()) {
                    productsStorageRepository.add(remoteData)
                    MediatorResult.Success(endOfPaginationReached = remoteData.isEmpty())
                } else MediatorResult.Error(Exception("Load data error!"))
            }
        }
    }

    private fun loadFakeData(
        loadType: LoadType,
        state: PagingState<Int, FakeProductEntity>
    ): List<FakeProductDTO> {
        val requestSize = when (loadType) {
            LoadType.REFRESH -> state.config.initialLoadSize
            else -> state.config.pageSize
        }
        return fakeRemoteData.generationFakeData(requestSize)
    }

    /**
     * Обновление на старте скипнул, потому что данных новых нет
     */
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }
}