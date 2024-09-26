package org.plux.marvelpedia.features.comics.comic_search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.plux.marvelpedia.commons.model.SearchScreenState
import org.plux.marvelpedia.commons.model.handleFilteredList
import org.plux.marvelpedia.features.comics.comic_list.data.use_cases.GetComicListUC
import org.plux.marvelpedia.features.comics.comic_list.model.Comic
import org.plux.marvelpedia.features.comics.comic_list.model.toDomain
import org.plux.marvelpedia.network.ApiResponse

class ComicSearchViewModel(
    private val getComicListUC: GetComicListUC
): ViewModel() {
    var state by mutableStateOf(SearchScreenState<Comic>())
        private set

    fun getList(nameFilter: String = "") {
        handleFilteredList(
            state = state,
            nameFilter = nameFilter,
            fetchList = { getFilteredList(state.nameFilter) },
            clearList = { state = state.copy(list = emptyList())}
        )
    }

    fun onBackPressed(){
        state = SearchScreenState()
    }

    private fun getFilteredList(nameFilter: String = "") = viewModelScope.launch(Dispatchers.IO) {
        getComicListUC(nameFilter = nameFilter).collectLatest { response ->
            when (response) {
                is ApiResponse.Error -> {
                    println(response.data)
                }

                is ApiResponse.Loading -> {
                    state = state.copy(isLoading = true)
                }

                is ApiResponse.Success -> {
                    val comics: List<Comic> =
                        response.data.data.results.map { it.toDomain() }
                    state = state.copy(list = comics, isLoading = false)
                }
            }
        }
    }
}