package org.plux.marvelpedia.commons.model

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState

@Composable
fun GridLaunchedEffect(
    listState: LazyGridState,
    buffer: Int,
    getScrollType: (ScrollingTypes) -> Unit,
    previousIndex: MutableState<Int>,
) {
    LaunchedEffect(listState) {

        val behaviour = ListBehaviourHandler.handle(
            state = listState,
            previousIndex = previousIndex.value,
            buffer = buffer
        )


        getScrollType.invoke(behaviour)

        //previousIndex.value = listState.firstVisibleItemIndex
    }
}









