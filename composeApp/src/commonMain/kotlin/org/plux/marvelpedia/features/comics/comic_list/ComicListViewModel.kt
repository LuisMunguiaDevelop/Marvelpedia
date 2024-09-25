package org.plux.marvelpedia.features.comics.comic_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.plux.marvelpedia.features.comics.comic_list.data.use_cases.GetComicListUC
import org.plux.marvelpedia.features.comics.comic_list.model.Comic
import org.plux.marvelpedia.features.comics.comic_list.model.toDomain
import org.plux.marvelpedia.network.ApiResponse

class ComicListViewModel(
    private val getComicListUC: GetComicListUC
) : ViewModel() {
    var state by mutableStateOf(ComicListState())
        private set

    init {
        getList()
    }

    fun fetchComics() {
        if (state.isFetching) return
        fetch()
        state = state.copy(isFetching = true)
    }

    private fun getList() = viewModelScope.launch(Dispatchers.IO) {
        getComicListUC(limit = 40).collectLatest { response ->
            when (response) {
                is ApiResponse.Error -> {
                    setLoading(false)
                }

                is ApiResponse.Loading -> {
                    setLoading(true)
                }

                is ApiResponse.Success -> {
                    val characters: List<Comic> =
                        response.data.data.results.map { it.toDomain() }
                    state.comicList.addAll(characters)
                    setLoading(false)
                }
            }
        }
    }

    private fun fetch() = viewModelScope.launch(Dispatchers.IO) {
        state = state.copy(isFetching = true)
        getComicListUC(offset = state.comicList.size).collectLatest { response ->
            when (response) {
                is ApiResponse.Error -> {
                    state = state.copy(isFetching = false)
                }

                is ApiResponse.Loading -> {
                    state = state.copy(isFetching = true)
                }

                is ApiResponse.Success -> {
                    val comics: List<Comic> =
                        response.data.data.results.map { it.toDomain() }
                    val newList: MutableList<Comic> = state.comicList.toMutableList()
                    newList.addAll(comics)
                    state = state.copy(
                        comicList = newList,
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