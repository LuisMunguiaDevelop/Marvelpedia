package org.plux.marvelpedia.commons.model

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun LazyListLaunchedEffect(
    listState: LazyGridState,
    buffer: Int = 5,
    onForwardScrollDetected: () -> Unit = {},
    onBackwardScrollDetected: () -> Unit = {},
    onFetchDetected: () -> Unit = {},
    onEndReached: () -> Unit = {}
){
    val lastScrollPosition = remember { mutableStateOf(listState.firstVisibleItemIndex) }
    val lastScrollType = remember { mutableStateOf(ScrollingTypes.NONE) }
    val lastFetchedAt = remember { mutableStateOf(0) }

    LaunchedEffect(
        key1 = listState.firstVisibleItemIndex,
        key2 = listState.layoutInfo.visibleItemsInfo.lastOrNull(),
        key3 = !listState.canScrollForward
    ) {

        //Check if user scrolled down
        if (listState.firstVisibleItemIndex > lastScrollPosition.value) {

            if (lastScrollType.value != ScrollingTypes.SCROLL_DOWN) {
                onBackwardScrollDetected.invoke()
                lastScrollType.value = ScrollingTypes.SCROLL_DOWN
            }

        }

        //Check if user scrolled up
        if (listState.firstVisibleItemIndex < lastScrollPosition.value) {
            if (lastScrollType.value != ScrollingTypes.SCROLL_UP) {
                onForwardScrollDetected.invoke()
                lastScrollType.value = ScrollingTypes.SCROLL_UP
            }
        }

        //set the lastScrollPosition to current position
        lastScrollPosition.value = listState.firstVisibleItemIndex


        if (
            listState.layoutInfo.visibleItemsInfo.lastOrNull() != null
            && listState.layoutInfo.visibleItemsInfo.lastOrNull()!!.index >= (listState.layoutInfo.totalItemsCount - buffer)
            && lastFetchedAt.value < (listState.layoutInfo.totalItemsCount - buffer)
        ) {
            onFetchDetected.invoke()
            lastFetchedAt.value = listState.layoutInfo.visibleItemsInfo.last().index
        }

        if(!listState.canScrollForward){
            onEndReached.invoke()
            lastScrollType.value = ScrollingTypes.END_REACHED
        }
    }
}









