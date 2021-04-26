package com.isanechek.productslist.data.mapper

import com.isanechek.productslist.data.tools.Mapper
import com.isanechek.productslist.models.BuyingProduct
import com.isanechek.productslist.models.FakeProduct

class ProductModelToBayingModelMapper : Mapper<FakeProduct, BuyingProduct> {

    override fun map(from: FakeProduct): BuyingProduct {
        return BuyingProduct(
            id = from.id,
            companyName = from.companyName,
            model = from.model,
            category = from.category,
            price = from.price,
            timestamp = from.timestamp,
            count = 0,
            coverUrl = from.coverUrl
        )
    }

    override fun map(from: List<FakeProduct>): List<BuyingProduct> {
        return from.map { product -> map(product) }.toList()
    }
}