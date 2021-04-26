package com.isanechek.productslist.di

import com.isanechek.productslist.usecases.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCasesModule = module {
    factory {
        LoadDataUseCase(get())
    }

    factory {
        LoadProductUseCase(get())
    }

    factory {
        AddToShoppingCartUseCase(get(), get(), get(named(PRODUCT_TO_BAYING_MODEL_MAPPER_NAMED)))
    }

    factory {
        LoadPriceUseCase(get())
    }

    factory {
        LoadBuyingDataUseCase(get())
    }

    factory {
        UpdateBuyingCountUseCase(get())
    }

    factory { RemoveBuyingProductUseCase(get()) }
}