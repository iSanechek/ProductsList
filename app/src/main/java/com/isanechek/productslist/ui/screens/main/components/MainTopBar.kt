package com.isanechek.productslist.ui.screens.main.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.res.stringResource
import com.isanechek.productslist.ui.components.CountIconButton
import com.isanechek.productslist.utils._str

@Composable
fun MainTopBar(countState: State<Int>, settingsClick: () -> Unit, openBasket: () -> Unit) {
    TopAppBar(title = {
        Text(text = stringResource(id = _str.main_top_bar_title))
    }, actions = {
        IconButton(settingsClick) {
            Icon(imageVector = Icons.Default.Settings, contentDescription = "list_settings_btn")
        }
        CountIconButton(
            countValue = countState.value,
            icon = Icons.Default.ShoppingBasket,
            contentDescription = "bbbb",
            openBasket
        )
    })
}