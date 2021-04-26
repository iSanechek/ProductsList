package com.isanechek.productslist.di

import com.isanechek.productslist.data.remote.fakedata.FakeRemoteData
import com.isanechek.productslist.data.remote.fakedata.impl.FakeRemoteDataImpl
import com.isanechek.productslist.data.repository.buying.BuyingRepository
import com.isanechek.productslist.data.repository.buying.impl.BuyingRepositoryImpl
import com.isanechek.productslist.data.repository.products.ProductsRepository
import com.isanechek.productslist.data.repository.products.impl.ProductsRepositoryImpl
import com.isanechek.productslist.data.repository.storage.BuyingStorageRepository
import com.isanechek.productslist.data.repository.storage.ProductsStorageRepository
import com.isanechek.productslist.data.repository.storage.impl.BuyingStorageRepositoryImpl
import com.isanechek.productslist.data.repository.storage.impl.ProductsStorageRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    single<FakeRemoteData> {
        FakeRemoteDataImpl()
    }

    single<ProductsStorageRepository> {
        ProductsStorageRepositoryImpl(get(), get(named(PRODUCT_DTO_TO_ENTITY_MAPPER_NAMED)))
    }

    single<ProductsRepository> {
        ProductsRepositoryImpl(get(), get(), get(named(PRODUCT_ENTITY_TO_MODEL_MAPPER_NAMED)), get())
    }

    single<BuyingStorageRepository> {
        BuyingStorageRepositoryImpl(
            get(named(PRODUCT_BAYING_MODEL_TO_ENTITY_MAPPER_NAMED)),
            get(named(PRODUCT_BAYING_ENTITY_TO_MODEL_MAPPER_NAMED)),
            get()
        )
    }

    single<BuyingRepository> {
        BuyingRepositoryImpl(get(), get())
    }
}