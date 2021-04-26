package com.isanechek.productslist.data.repository.storage.impl

import androidx.paging.PagingSource
import androidx.sqlite.db.SimpleSQLiteQuery
import com.isanechek.productslist.data.tools.Mapper
import com.isanechek.productslist.data.local.dao.ProductsDao
import com.isanechek.productslist.data.local.entity.FakeProductEntity
import com.isanechek.productslist.data.remote.dto.FakeProductDTO
import com.isanechek.productslist.data.repository.storage.ProductsStorageRepository

class ProductsStorageRepositoryImpl(
    private val productsDao: ProductsDao,
    private val mapper: Mapper<FakeProductDTO, FakeProductEntity>
) : ProductsStorageRepository {

    override suspend fun add(items: List<FakeProductDTO>) {
        val data = mapper.map(items)
        productsDao.insert(data)
    }

    override suspend fun refresh(items: List<FakeProductDTO>) {
        val data = mapper.map(items)
        productsDao.refreshData(data)
    }

    override fun loadPaged(): PagingSource<Int, FakeProductEntity> {
        return productsDao.loadPaging()
    }

    override fun loadSortPaged(request: SimpleSQLiteQuery): PagingSource<Int, FakeProductEntity> {
        return productsDao.loadSortingPaging(request)
    }

    override suspend fun loadProduct(productId: String): FakeProductEntity? {
        return productsDao.loadProduct(productId)
    }
}