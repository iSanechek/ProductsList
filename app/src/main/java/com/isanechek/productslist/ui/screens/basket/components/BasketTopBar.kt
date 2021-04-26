package com.isanechek.productslist.ui.screens.basket.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.isanechek.productslist.utils._str

@Composable
fun BasketTopBar(popUp: () -> Unit) {
    TopAppBar(title = {
        Text(text = stringResource(id = _str.basket_title))
    }, navigationIcon = {
        IconButton(popUp) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = _str.basket_nav_close_btn)
            )
        }
    })
}