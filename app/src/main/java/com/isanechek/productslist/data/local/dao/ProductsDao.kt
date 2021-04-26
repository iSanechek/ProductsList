package com.isanechek.productslist.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.isanechek.productslist.data.local.entity.FakeProductEntity
import com.isanechek.productslist.models.FakeProduct

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<FakeProductEntity>)

    @Query("SELECT * FROM fake_products_table ORDER BY timestamp DESC")
    suspend fun load(): List<FakeProductEntity>

    @Query("SELECT * FROM fake_products_table ORDER BY timestamp DESC")
    fun loadPaging(): PagingSource<Int, FakeProductEntity>

    @RawQuery(observedEntities = [(FakeProductEntity::class)])
    fun loadSortingPaging(request: SimpleSQLiteQuery): PagingSource<Int, FakeProductEntity>

    @Query("DELETE FROM fake_products_table")
    suspend fun clear()

    @Query("SELECT * FROM fake_products_table WHERE id =:productId")
    suspend fun loadProduct(productId: String): FakeProductEntity?

    @Transaction
    suspend fun refreshData(data: List<FakeProductEntity>) {
        clear()
        insert(data)
    }
}