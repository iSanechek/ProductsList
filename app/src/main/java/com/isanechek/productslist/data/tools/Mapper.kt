package com.isanechek.productslist.data.tools

interface Mapper<F, T> {
    fun map(from: F): T
    fun map(from: List<F>): List<T>
}