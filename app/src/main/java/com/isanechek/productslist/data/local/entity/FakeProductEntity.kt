package com.isanechek.productslist.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fake_products_table")
data class FakeProductEntity(
    @PrimaryKey val id: String,
    val model: String,
    val category: String,
    @ColumnInfo(name = "company_name") val companyName: String,
    val price: Int,
    val timestamp: Long,
    @ColumnInfo(name = "cover_url") val coverUrl: String,
    val description: String
)
