package com.isanechek.productslist.ui.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExposureNeg1
import androidx.compose.material.icons.filled.ExposurePlus1
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.isanechek.productslist.models.DialogData

/**
 * Тут маленький адок
 */
@Composable
fun MainDialogAddBasket(
    state: MutableState<DialogData>,
    addToBasket: () -> Unit,
    deleteProduct: (String) -> Unit
) {
    val count = remember {
        mutableStateOf(1)
    }
    if (state.value.isShow) {
        if (state.value.isUpdate) {
            count.value = state.value.count
        }
        Dialog(onDismissRequest = { state.value = state.value.copy(isShow = false) }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(if (state.value.isUpdate) 250.dp else 200.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 8.dp), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (state.value.isUpdate) "В корзине" else "Добавить в корзину",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = String.format("%s %d шт", state.value.title, count.value),
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(200.dp)
                ) {
                    ClickIcon(
                        modifier = Modifier
                            .weight(1f),
                        icon = Icons.Default.ExposureNeg1,
                        enable = count.value != 0
                    ) {
                        count.value = count.value.minus(1)
                    }

                    ClickIcon(
                        modifier = Modifier
                            .weight(1f),
                        icon = Icons.Default.ExposurePlus1,
                        true
                    ) {
                        count.value = count.value.plus(1)
                    }

                }

                Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                    Row {
                        OutlinedButton(
                            onClick = {
                                state.value = state.value.copy(isShow = false)
                            }, modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                        ) {
                            Text(text = "отменить")
                        }
                        Button(
                            onClick = {
                                state.value = state.value.copy(isShow = false, count = count.value)
                                addToBasket()
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp),
                            enabled = count.value != 0
                        ) {
                            Text(text = if (state.value.isUpdate) "обновить" else "добавить")
                        }
                    }

                    if (state.value.isUpdate) {
                        Button(
                            onClick = {
                                state.value = state.value.copy(isShow = false)
                                deleteProduct(state.value.productId)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        ) {
                            Text(text = "Удалить все")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ClickIcon(modifier: Modifier, icon: ImageVector, enable: Boolean, click: () -> Unit) {

    IconButton(click, modifier = modifier, enabled = enable) {
        Icon(
            imageVector = icon,
            contentDescription = "boom"
        )
    }
}