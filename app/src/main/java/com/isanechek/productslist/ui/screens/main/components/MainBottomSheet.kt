package com.isanechek.productslist.ui.screens.main.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isanechek.productslist.models.SortingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainBottomSheet(
    state: BottomSheetScaffoldState,
    scope: CoroutineScope,
    sortingState: MutableState<SortingState>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        Text(
            text = "Сортировка",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )

        Row(Modifier.fillMaxWidth()) {
            Text(
                text = "Сортировать по имени",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Checkbox(
                checked = sortingState.value !is SortingState.Default,
                modifier = Modifier.weight(.2f),
                onCheckedChange = { select ->
                    if (select) {
                        sortingState.value = SortingState.SortingName(false)
                    } else {
                        sortingState.value = SortingState.Default
                    }
                })
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )

        Button(onClick = {
            scope.launch {
                state.bottomSheetState.collapse()
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(start = 8.dp, end = 8.dp)) {
            Text(text = "закрыть")
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )

    }
}