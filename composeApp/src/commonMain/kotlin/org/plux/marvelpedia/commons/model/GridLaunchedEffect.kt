package org.plux.marvelpedia.commons.model

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun LazyListLaunchedEffect(
    listState: LazyGridState,
    buffer: Int = 5,
    onForwardScrollDetected: () -> Unit,
    onBackwardScrollDetected: () -> Unit,
    onFetchDetected: () -> Unit,
    onEndReached: () -> Unit,
    getScrollableEvents: Boolean = true,
    getFetchEvents: Boolean = true,
    getEndReachedEvents: Boolean = true
) {
    val lastScrollPosition = remember { mutableStateOf(listState.firstVisibleItemIndex) }
    val lastScrollType = remember { mutableStateOf(ScrollingTypes.NONE) }
    val lastFetchedAt = remember { mutableStateOf(0) }


    Box {
        if(getScrollableEvents)
        LaunchedEffect(listState.firstVisibleItemIndex) {

            if (listState.firstVisibleItemIndex < lastScrollPosition.value && lastScrollType.value != ScrollingTypes.SCROLL_BACKWARDS) {
                lastScrollType.value = ScrollingTypes.SCROLL_BACKWARDS
                onBackwardScrollDetected.invoke()
            }

            if (listState.firstVisibleItemIndex > lastScrollPosition.value && lastScrollType.value != ScrollingTypes.SCROLL_FORWARD) {
                lastScrollType.value = ScrollingTypes.SCROLL_FORWARD
                onForwardScrollDetected.invoke()
            }

            lastScrollPosition.value = listState.firstVisibleItemIndex
        }

        if(getFetchEvents)
        LaunchedEffect(
            key1 = listState.layoutInfo.visibleItemsInfo.lastOrNull() != null
                    && listState.layoutInfo.visibleItemsInfo.lastOrNull()!!.index >= (listState.layoutInfo.totalItemsCount - buffer)
                    && lastFetchedAt.value < (listState.layoutInfo.totalItemsCount - buffer),
        ) {

            if (
                listState.layoutInfo.visibleItemsInfo.lastOrNull() != null
                && listState.layoutInfo.visibleItemsInfo.lastOrNull()!!.index >= (listState.layoutInfo.totalItemsCount - buffer)
                && lastFetchedAt.value < (listState.layoutInfo.totalItemsCount - buffer)
            ) {
                onFetchDetected.invoke()
                lastFetchedAt.value = listState.layoutInfo.visibleItemsInfo.last().index
            }


        }

        if(getEndReachedEvents)
        LaunchedEffect(listState.canScrollForward) {
            if (!listState.canScrollForward) onEndReached.invoke()
        }
    }
}











