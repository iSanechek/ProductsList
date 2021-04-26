package com.isanechek.productslist.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.ShoppingBasket
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.isanechek.productslist.models.FakeProduct
import com.isanechek.productslist.models.PriceProduct
import com.isanechek.productslist.models.UiState
import com.isanechek.productslist.ui.components.CountIconButton
import com.isanechek.productslist.utils._str
import com.isanechek.productslist.utils.log
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(productId: String?, vm: DetailViewModel = getViewModel(), popUp: () -> Unit) {
    val titleState = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val offset = remember { mutableStateOf(0f) }
    val scrollState = rememberScrollableState { delta ->
        offset.value = offset.value + delta // update the state
        delta // indicate that we consumed all the pixels available
    }

    val priceState = vm.uiPriceState.collectAsState()
    val uiState = vm.uiState.collectAsState()
    val addUiState = vm.addUiState.collectAsState()
    when (addUiState.value) {
        is UiState.Fail -> {
            log { "FAIL ADD" }
        }
        is UiState.Empty -> {
            log { "EMPTY ADD" }
        }
        is UiState.Done -> {
            log { "DONE ADD" }
        }
        is UiState.Progress -> {
            log { "PROGRESS ADD" }
        }
    }

    LaunchedEffect(productId) {
        vm.loadProduct(productId)
    }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            ProductCountAdd { count ->
                scope.launch {
                    scaffoldState.bottomSheetState.collapse()
                }
                vm.addBasket(productId, count)
            }
        },
        topBar = {
            TopBar(title = titleState, priceState, popUp, openBottom = {})
        }) { paddingValues ->
        when (val state = uiState.value) {
            is UiState.Empty -> {
            }
            is UiState.Done -> {
                val data = state.data
                titleState.value = data.companyName
                Content(product = data, paddingValues = paddingValues, scrollState = scrollState) {
                    scope.launch {
                        if (scaffoldState.bottomSheetState.isExpanded) {
                            scaffoldState.bottomSheetState.collapse()
                        } else {
                            scaffoldState.bottomSheetState.expand()
                        }
                    }
                }
            }
            is UiState.Fail -> {
            }
        }

    }
}

@Composable
private fun ProductCountAdd(addProduct: (Int) -> Unit) {
    val count = remember {
        mutableStateOf(1)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {

        Row(modifier = Modifier.align(Alignment.Center)) {
            Button(
                onClick = { count.value = count.value.minus(1) },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "-")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "${count.value}", modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { count.value = count.value.plus(1) },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "+")
            }
        }

        Button(
            onClick = { addProduct(count.value) },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "Add in ${count.value}")
        }
    }
}

@Composable
private fun Content(
    product: FakeProduct,
    paddingValues: PaddingValues,
    scrollState: ScrollableState,
    openCountDialog: () -> Unit
) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(paddingValues)
                .scrollable(state = scrollState, orientation = Orientation.Vertical)
        ) {
            CoverSection(coverUrl = product.coverUrl)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            InfoSection(product = product)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Button(
                onClick = openCountDialog, modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {
                Text(text = "Add")
            }
        }
    }
}

@Composable
private fun InfoSection(product: FakeProduct) {
    Card(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(text = product.category)
            Text(text = product.companyName)
            Text(text = product.model)
            Text(text = "${product.price}")
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            Text(
                text = product.description,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
private fun CoverSection(coverUrl: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .align(Alignment.Center),
            elevation = 4.dp
        ) {
            Image(
                painter = rememberCoilPainter(request = coverUrl, fadeIn = true),
                contentDescription = stringResource(
                    id = _str.detail_cover
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun TopBar(
    title: MutableState<String>,
    priceState: State<PriceProduct>,
    popUp: () -> Unit,
    openBottom: () -> Unit
) {
    TopAppBar(title = {
        Text(text = title.value)
    }, navigationIcon = {
        IconButton(popUp) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = _str.detail_product_close_btn)
            )
        }
    }, actions = {
        CountIconButton(
            countValue = priceState.value.productCount,
            icon = Icons.Rounded.ShoppingBasket,
            contentDescription = stringResource(id = _str.detail_shopping_btn),
            click = openBottom
        )
    })
}