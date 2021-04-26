package com.isanechek.productslist.utils

import android.util.Log

fun log(message: () -> String) {
    Log.e("DEBUG", message())
}