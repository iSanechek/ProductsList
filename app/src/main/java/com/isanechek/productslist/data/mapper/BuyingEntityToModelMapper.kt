package com.isanechek.productslist.data.mapper

import com.isanechek.productslist.data.tools.Mapper
import com.isanechek.productslist.data.local.entity.BuyingProductEntity
import com.isanechek.productslist.models.BuyingProduct

class BuyingEntityToModelMapper : Mapper<BuyingProductEntity, BuyingProduct> {

    override fun map(from: BuyingProductEntity): BuyingProduct {
        return BuyingProduct(
            id = from.id,
            model = "",
            category = "",
            companyName = "",
            price = 0,
            timestamp = from.timestamp,
            count = from.count,
            coverUrl = ""
        )
    }

    override fun map(from: List<BuyingProductEntity>): List<BuyingProduct> {
        return from.map { product -> map(product) }.toList()
    }
}