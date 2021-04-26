package com.isanechek.productslist.ui.screens.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleItem(modifier: Modifier, companyName: String, model: String, category: String) {
    Row(modifier = modifier) {
        Spacer(modifier = Modifier.height(170.dp))
        Column {
            Text(text = companyName, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 12.sp)) {
                    append("model: ")
                }
                withStyle(style = SpanStyle(color = Color.Black, fontSize = 14.sp)) {
                    append(model)
                }
            })
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 12.sp)) {
                    append("price: ")
                }
                withStyle(style = SpanStyle(color = Color.Green, fontSize = 12.sp)) {
                    append("$")
                }
                withStyle(style = SpanStyle(fontSize = 12.sp)) {
                    append("12.000")
                }
            })
        }
    }
}