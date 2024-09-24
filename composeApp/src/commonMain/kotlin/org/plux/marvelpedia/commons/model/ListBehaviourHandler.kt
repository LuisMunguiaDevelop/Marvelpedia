package org.plux.marvelpedia.commons.model

import androidx.compose.foundation.lazy.grid.LazyGridState

object ListBehaviourHandler {
    private const val LIST_INDEX_BUFFER = 5

    fun handle(
        state: LazyGridState,
        buffer: Int = LIST_INDEX_BUFFER,
        previousIndex: Int,
    ): ScrollingTypes{
        if(state.layoutInfo.visibleItemsInfo.lastOrNull() == null) return ScrollingTypes.NONE

        if(state.layoutInfo.visibleItemsInfo.last().index >= (state.layoutInfo.totalItemsCount)-buffer){
            return ScrollingTypes.FETCH
        }

        if (state.firstVisibleItemIndex > previousIndex) {
            return ScrollingTypes.SCROLL_DOWN
        } else if (state.firstVisibleItemIndex < previousIndex) {
            return ScrollingTypes.SCROLL_UP
        }
        return ScrollingTypes.NONE
    }
}
