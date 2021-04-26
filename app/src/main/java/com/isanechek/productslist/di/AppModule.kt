package com.isanechek.productslist.di

import com.isanechek.productslist.ui.screens.basket.BasketViewModel
import com.isanechek.productslist.ui.screens.detail.DetailViewModel
import com.isanechek.productslist.ui.screens.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel(get(), get())
    }

    viewModel {
        DetailViewModel(get(), get(), get())
    }

    viewModel {
        BasketViewModel(get(), get(), get())
    }
}