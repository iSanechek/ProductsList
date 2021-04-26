package com.isanechek.productslist.models

data class DialogData(
    var isShow: Boolean,
    val title: String,
    val productId: String,
    val count: Int,
    val price: Int,
    val isUpdate: Boolean
) {
    companion object {
        fun empty() = DialogData(false, "", "", 1, 0, false)

        fun calculate(price: Int, count: Int): Int = price * count
    }
}
