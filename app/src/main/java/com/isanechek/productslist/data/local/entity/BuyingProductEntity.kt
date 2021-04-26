package com.isanechek.productslist.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "buying_products")
data class BuyingProductEntity(@PrimaryKey val id: String,
                               val timestamp: Long,
                               val count: Int)
