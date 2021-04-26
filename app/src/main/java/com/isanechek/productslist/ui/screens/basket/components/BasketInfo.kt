package com.isanechek.productslist.ui.screens.basket.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BasketInfo(modifier: Modifier, count: Int, totalPrice: Int) {
    Row(modifier = modifier.padding(start = 8.dp, bottom = 8.dp)) {
        BasketText(text = "количество $count")
        Spacer(modifier = Modifier.width(8.dp))
        BasketText(text = "всего на сумму $totalPrice")
    }
}

@Composable
private fun BasketText(text: String) {
    Text(text = text, fontSize = 12.sp)
}