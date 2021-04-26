package com.isanechek.productslist.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CountIconButton(
    countValue: Int,
    icon: ImageVector,
    contentDescription: String?,
    click: () -> Unit
) {
    IconButton(click) {
        Box(modifier = Modifier.wrapContentSize()) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier
                    .align(
                        Alignment.Center
                    )
                    .padding(4.dp)
            )
            AnimatedVisibility(visible = countValue != 0) {
                Text(
                    text = "$countValue",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .background(
                            color = Color.Red.copy(alpha = .8f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(start = 4.dp, end = 4.dp), fontSize = 12.sp
                )
            }
        }
    }
}