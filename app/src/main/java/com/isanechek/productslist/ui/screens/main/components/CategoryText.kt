package com.isanechek.productslist.ui.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryText(modifier: Modifier, category: String) {
    val boxModifier = Modifier
        .padding(start = 8.dp, bottom = 8.dp)
        .background(color = Color.Gray.copy(alpha = .5f), shape = RoundedCornerShape(2.dp))
    Box(
        modifier = modifier.then(boxModifier)
    ) {
        Text(
            text = category,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 2.dp, end = 2.dp, bottom = 2.dp),
            fontSize = 12.sp
        )
    }
}