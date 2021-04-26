package com.isanechek.productslist.di

import com.isanechek.productslist.data.tools.Mapper
import com.isanechek.productslist.data.local.entity.BuyingProductEntity
import com.isanechek.productslist.data.local.entity.FakeProductEntity
import com.isanechek.productslist.data.mapper.*
import com.isanechek.productslist.data.remote.dto.FakeProductDTO
import com.isanechek.productslist.models.BuyingProduct
import com.isanechek.productslist.models.FakeProduct
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val PRODUCT_DTO_TO_ENTITY_MAPPER_NAMED = "product_dto_to_entity"
const val PRODUCT_ENTITY_TO_MODEL_MAPPER_NAMED = "product_entity_to_model"
const val PRODUCT_TO_BAYING_MODEL_MAPPER_NAMED = "product_to_buying_model"
const val PRODUCT_BAYING_MODEL_TO_ENTITY_MAPPER_NAMED = "buying_model_to_entity"
const val PRODUCT_BAYING_ENTITY_TO_MODEL_MAPPER_NAMED = "buying_entity_to_model"

val mappersModule = module {

    factory<Mapper<FakeProductDTO, FakeProductEntity>>(named(PRODUCT_DTO_TO_ENTITY_MAPPER_NAMED)) {
        ProductDtoToEntityMapper()
    }

    factory<Mapper<FakeProductEntity, FakeProduct>>(named(PRODUCT_ENTITY_TO_MODEL_MAPPER_NAMED)) {
        ProductsEntityToModelMapper()
    }

    factory<Mapper<FakeProduct, BuyingProduct>>(named(PRODUCT_TO_BAYING_MODEL_MAPPER_NAMED)) {
        ProductModelToBayingModelMapper()
    }

    factory<Mapper<BuyingProduct, BuyingProductEntity>>(named(PRODUCT_BAYING_MODEL_TO_ENTITY_MAPPER_NAMED)) {
        BuyingModelToEntityMapper()
    }

    factory<Mapper<BuyingProductEntity, BuyingProduct>>(named(PRODUCT_BAYING_ENTITY_TO_MODEL_MAPPER_NAMED)) {
        BuyingEntityToModelMapper()
    }
}