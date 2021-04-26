package com.isanechek.productslist.data.mapper

import com.isanechek.productslist.data.tools.Mapper
import com.isanechek.productslist.data.local.entity.BuyingProductEntity
import com.isanechek.productslist.models.BuyingProduct

class BuyingModelToEntityMapper : Mapper<BuyingProduct, BuyingProductEntity> {

    override fun map(from: BuyingProduct): BuyingProductEntity {
        return BuyingProductEntity(id = from.id, timestamp = from.timestamp, count = from.count)
    }

    override fun map(from: List<BuyingProduct>): List<BuyingProductEntity> {
        return from.map { product -> map(product) }.toList()
    }
}