package com.isanechek.productslist.usecases

import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.sqlite.db.SimpleSQLiteQuery
import com.isanechek.productslist.data.repository.products.ProductsRepository
import com.isanechek.productslist.models.FakeProduct
import com.isanechek.productslist.models.SortingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class LoadDataUseCase(
    private val productsRepository: ProductsRepository
) {

    val buyingProducts: Flow<Int> get() = productsRepository.buyingCount

    fun loadData(scope: CoroutineScope): Flow<PagingData<FakeProduct>> {
        return productsRepository.load().cachedIn(scope)
    }

    fun loadSortData(scope: CoroutineScope, sortingState: SortingState): Flow<PagingData<FakeProduct>> {

        val sortRequest = when(sortingState) {
            is SortingState.SortingName -> {
                if (sortingState.desc) {
                    SimpleSQLiteQuery("SELECT * FROM fake_products_table ORDER BY company_name DESC")
                } else {
                    SimpleSQLiteQuery("SELECT * FROM fake_products_table ORDER BY company_name ASC")
                }
            }
            is SortingState.Default -> {
                SimpleSQLiteQuery("SELECT * FROM fake_products_table ORDER BY timestamp DESC")
            }
            else -> SimpleSQLiteQuery("SELECT * FROM fake_products_table ORDER BY timestamp DESC")
        }

        return productsRepository.loadSort(sortRequest).cachedIn(scope)
    }
}