package com.isanechek.productslist.models

sealed class SortingState {
    data class SortingName(val desc: Boolean) : SortingState()
    object Default : SortingState()
}
