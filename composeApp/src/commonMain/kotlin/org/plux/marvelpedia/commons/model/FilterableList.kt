package org.plux.marvelpedia.commons.model

interface FilterableState<T> {
    val list: List<T>
    var nameFilter: String
}


fun <T> handleFilteredList(
    state: FilterableState<T>,
    nameFilter: String,
    fetchList: (String) -> Unit,
    clearList: () -> Unit,
) {
    if (nameFilter == state.nameFilter) return
    if (nameFilter.isBlank()) {
        state.nameFilter = nameFilter
        clearList.invoke()
        return
    }

    state.nameFilter = nameFilter

    fetchList.invoke(state.nameFilter)
}
