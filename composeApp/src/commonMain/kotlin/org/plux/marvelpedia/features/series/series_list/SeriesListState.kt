package org.plux.marvelpedia.features.series.series_list

import org.plux.marvelpedia.features.series.series_list.model.Serie

data class SeriesListState(
    val seriesList: MutableList<Serie> = mutableListOf(),
    var isLoading: Boolean = true,
    var isFetching: Boolean = false,
)