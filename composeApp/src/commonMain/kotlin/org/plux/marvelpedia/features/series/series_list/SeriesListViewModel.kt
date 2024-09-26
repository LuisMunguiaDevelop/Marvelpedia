package org.plux.marvelpedia.features.series.series_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.plux.marvelpedia.features.series.series_list.data.GetSeriesListUC
import org.plux.marvelpedia.features.series.series_list.model.Serie
import org.plux.marvelpedia.features.series.series_list.model.toDomain
import org.plux.marvelpedia.network.ApiResponse

class SeriesListViewModel(
    private val getSeriesListUC: GetSeriesListUC
) : ViewModel() {
    var state by mutableStateOf(SeriesListState())
        private set

    init {
        getList()
    }

    fun fetchSeries() {
        if (state.isFetching) return
        fetch()
        state = state.copy(isFetching = true)
    }

    private fun getList() = viewModelScope.launch(Dispatchers.IO) {
        getSeriesListUC(limit = 40).collectLatest { response ->
            when (response) {
                is ApiResponse.Error -> {
                    setLoading(false)
                }

                is ApiResponse.Loading -> {
                    setLoading(true)
                }

                is ApiResponse.Success -> {
                    val series: List<Serie> =
                        response.data.data.results.map { it.toDomain() }
                    state.seriesList.addAll(series)
                    setLoading(false)
                }
            }
        }
    }

    private fun fetch() = viewModelScope.launch(Dispatchers.IO) {
        state = state.copy(isFetching = true)
        getSeriesListUC(offset = state.seriesList.size).collectLatest { response ->
            when (response) {
                is ApiResponse.Error -> {
                    state = state.copy(isFetching = false)
                }

                is ApiResponse.Loading -> {
                    state = state.copy(isFetching = true)
                }

                is ApiResponse.Success -> {
                    val series: List<Serie> =
                        response.data.data.results.map { it.toDomain() }
                    val newList: MutableList<Serie> = state.seriesList.toMutableList()
                    newList.addAll(series)
                    state = state.copy(
                        seriesList = newList,
                        isFetching = false
                    )
                }
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        state = state.copy(isLoading = loading)
    }
}