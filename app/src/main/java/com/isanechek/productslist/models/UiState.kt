package com.isanechek.productslist.models

sealed class UiState<out T : Any> {
    data class Progress(val message: String?) : UiState<Nothing>()
    data class Fail(val errorMessage: String) : UiState<Nothing>()
    data class Done<out T : Any>(val data: T) : UiState<T>()
    object Empty : UiState<Nothing>()
}
