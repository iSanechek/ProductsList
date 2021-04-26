package com.isanechek.productslist.ui.screens.main.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.isanechek.productslist.models.FakeProduct
import com.isanechek.productslist.utils._str

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListProductItem(
    product: FakeProduct,
    openDetail: (String) -> Unit,
    openDialog: (String, String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
    ) {
        // Content card
        Card(
            Modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(start = 8.dp, end = 8.dp)
                .align(Alignment.Center)
                .combinedClickable(onLongClick = {
                    openDetail(product.id)
                }, onClick = {
                    openDialog(
                        product.id,
                        String.format("%s %s", product.companyName, product.model)
                    )
                }),
            elevation = 2.dp
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                TitleItem(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 8.dp, top = 8.dp),
                    companyName = product.companyName,
                    model = product.model, category = product.category
                )
                CategoryText(
                    modifier = Modifier.align(Alignment.BottomStart),
                    category = product.category
                )
            }
        }

        // Cover card
        Card(
            Modifier
                .fillMaxHeight()
                .width(140.dp)
                .padding(top = 4.dp, bottom = 4.dp, end = 28.dp)
                .align(Alignment.CenterEnd),
            elevation = 2.dp
        ) {
            Image(
                painter = rememberCoilPainter(request = product.coverUrl, fadeIn = true),
                contentDescription = stringResource(
                    id = _str.main_list_item_description
                ),
                contentScale = ContentScale.Crop
            )
        }
    }
}