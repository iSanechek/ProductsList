package com.isanechek.productslist.data.mapper

import com.isanechek.productslist.data.tools.Mapper
import com.isanechek.productslist.data.local.entity.FakeProductEntity
import com.isanechek.productslist.data.remote.dto.FakeProductDTO
import java.util.*

class ProductDtoToEntityMapper : Mapper<FakeProductDTO, FakeProductEntity> {

    override fun map(from: FakeProductDTO): FakeProductEntity {
        val productId = UUID.randomUUID().toString()
        return FakeProductEntity(
            id = productId,
            model = from.model,
            category = from.category,
            timestamp = from.timestamp,
            price = from.price,
            description = from.description,
            companyName = from.companyName,
            coverUrl = from.coverUrl
        )
    }

    override fun map(from: List<FakeProductDTO>): List<FakeProductEntity> {
        return from.map { item -> map(item) }.toList()
    }
}