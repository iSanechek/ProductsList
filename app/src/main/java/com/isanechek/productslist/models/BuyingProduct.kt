package com.isanechek.productslist.models

data class BuyingProduct(
    val id: String,
    val model: String,
    val category: String,
    val companyName: String,
    val price: Int,
    val timestamp: Long,
    val count: Int,
    val coverUrl: String
)
