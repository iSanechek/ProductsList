package com.isanechek.productslist.data.remote.dto

data class FakeProductDTO(val model: String,
                          val category: String,
                          val companyName: String,
                          val price: Int,
                          val timestamp: Long,
                          val description: String,
                          val coverUrl: String)
