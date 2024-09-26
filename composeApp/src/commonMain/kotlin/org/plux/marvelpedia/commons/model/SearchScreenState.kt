package org.plux.marvelpedia.commons.model


data class SearchScreenState<T>(
    override val list: List<T> = listOf(),
    override var nameFilter: String = "",
    var isLoading: Boolean = false,
    var isFetching: Boolean = false,
    var hasActualItems: Boolean = false,
): FilterableState<T>