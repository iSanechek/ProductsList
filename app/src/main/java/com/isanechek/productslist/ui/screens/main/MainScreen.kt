package com.isanechek.productslist.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.isanechek.productslist.models.DialogData
import com.isanechek.productslist.models.SortingState
import com.isanechek.productslist.ui.screens.main.components.MainBottomSheet
import com.isanechek.productslist.ui.screens.main.components.MainContent
import com.isanechek.productslist.ui.screens.main.components.MainDialogAddBasket
import com.isanechek.productslist.ui.screens.main.components.MainTopBar
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(vm: MainViewModel = getViewModel(), openDetail: (String) -> Unit, openBasket: () -> Unit) {
    /**
     * scope - Нужен для запуска suspend внутри composable функции
     */
    val scope = rememberCoroutineScope()

    /**
     * scrollState - запоминает состояние листа
     * Это вроде как работает из коробки, но не работает
     * Есть мысль что из-за неправильной компоновки композиции
     */
    val scrollState = rememberLazyListState()

    /**
     * scaffoldState - запоминает состояние и позволяет
     * рулить состоянием BottomSheet
     */
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    val buyingCount = vm.buyingProducts.collectAsState(initial = 0)

    /**
     * addBasketDialogState - состояние AlertDialog
     */
    val addBasketDialogState =
        remember { mutableStateOf(DialogData.empty()) }

    /**
     * Собственно сам диалог
     */
    MainDialogAddBasket(state = addBasketDialogState, addToBasket = {
        vm.addToShoppingCart(addBasketDialogState.value.productId, addBasketDialogState.value.count)
    }, deleteProduct = {})

    /**
     * sortState - запоминает состаяние сортировки
     */
    val sortState = remember {
        mutableStateOf<SortingState>(SortingState.Default)
    }

    /**
     * BottomSheetScaffold - эдакий layout, который позволяет нам юзать BottomSheet and etc
     * Подробности тут https://developer.android.com/reference/kotlin/androidx/compose/material/BottomSheetScaffoldState
     */
    BottomSheetScaffold(
        sheetContent = { MainBottomSheet(state = scaffoldState, scope = scope, sortingState = sortState) },
        sheetPeekHeight = 0.dp,
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            MainTopBar(countState = buyingCount, settingsClick = {
                scope.launch {
                    if (scaffoldState.bottomSheetState.isExpanded) {
                        scaffoldState.bottomSheetState.collapse()
                    } else {
                        scaffoldState.bottomSheetState.expand()
                    }
                }
            }, openBasket)
        }
    ) { paddingValues ->
        MainContent(
            paddingValues = paddingValues,
            scrollState = scrollState,
            data = vm.loadProducts(sortState.value),
            openDetail = {}, /** Нужно доделывать. */
            openDialog = { productId, title ->
                addBasketDialogState.value = addBasketDialogState.value.copy(
                    isShow = true,
                    title = title,
                    productId = productId
                )
            }
        )
    }
}

