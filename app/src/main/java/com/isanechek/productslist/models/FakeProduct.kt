package com.isanechek.productslist.models

data class FakeProduct(
    val id: String,
    val model: String,
    val category: String,
    val companyName: String,
    val price: Int,
    val timestamp: Long,
    val coverUrl: String,
    val description: String
)
