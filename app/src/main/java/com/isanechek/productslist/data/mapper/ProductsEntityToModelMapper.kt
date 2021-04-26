package com.isanechek.productslist.data.mapper

import com.isanechek.productslist.data.tools.Mapper
import com.isanechek.productslist.data.local.entity.FakeProductEntity
import com.isanechek.productslist.models.FakeProduct

class ProductsEntityToModelMapper : Mapper<FakeProductEntity, FakeProduct> {

    override fun map(from: FakeProductEntity): FakeProduct {
        return FakeProduct(
            id = from.id,
            model = from.model,
            companyName = from.companyName,
            category = from.category,
            timestamp = from.timestamp,
            price = from.price,
            coverUrl = from.coverUrl,
            description = from.description
        )
    }

    override fun map(from: List<FakeProductEntity>): List<FakeProduct> {
        return from.map { item -> map(item) }.toList()
    }
}