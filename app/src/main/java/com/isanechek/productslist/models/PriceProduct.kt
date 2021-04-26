package com.isanechek.productslist.models

data class PriceProduct(val price: Int, val productCount: Int, val totalPrice: Int) {

    companion object {
        fun empty() = PriceProduct(0, 0, 0)
    }
}
