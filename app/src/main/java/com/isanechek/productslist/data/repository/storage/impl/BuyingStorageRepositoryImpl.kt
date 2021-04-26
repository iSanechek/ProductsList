package com.isanechek.productslist.data.repository.storage.impl

import com.isanechek.productslist.data.local.dao.BuyingProductsDao
import com.isanechek.productslist.data.local.entity.BuyingProductEntity
import com.isanechek.productslist.data.repository.storage.BuyingStorageRepository
import com.isanechek.productslist.data.tools.Mapper
import com.isanechek.productslist.models.BuyingProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BuyingStorageRepositoryImpl(
    private val mapperToEntity: Mapper<BuyingProduct, BuyingProductEntity>,
    private val mapperToModel: Mapper<BuyingProductEntity, BuyingProduct>,
    private val buyingProductsDao: BuyingProductsDao
) : BuyingStorageRepository {

    override val countProducts: Flow<Int>
        get() = buyingProductsDao.count()

    override suspend fun add(product: BuyingProduct) {
        buyingProductsDao.insert(mapperToEntity.map(product))
    }

    override fun load(): Flow<List<BuyingProduct>> {
        return buyingProductsDao.load().map { data -> mapperToModel.map(data) }
    }

    override suspend fun get(productId: String): BuyingProduct? {
        val buying = buyingProductsDao.loadProduct(productId)
        return if (buying != null) {
            mapperToModel.map(buying)
        } else null
    }

    override suspend fun updateCount(productId: String, count: Int) {
        buyingProductsDao.updateCount(productId, count)
    }

    override suspend fun removeProduct(productId: String) {
        buyingProductsDao.remove(productId)
    }

    override fun priceCount(productId: String): Flow<Int> {
        return buyingProductsDao.priceCount(productId)
    }
}