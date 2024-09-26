package org.plux.marvelpedia.features.creators.creators_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.plux.marvelpedia.features.creators.creators_list.data.GetCreatorsListUC
import org.plux.marvelpedia.features.creators.creators_list.model.Creator
import org.plux.marvelpedia.features.creators.creators_list.model.toDomain
import org.plux.marvelpedia.network.ApiResponse

class CreatorsListViewModel(
    private val getCreatorsListUC: GetCreatorsListUC
) : ViewModel() {
    var state by mutableStateOf(CreatorsListState())
        private set

    init {
        getList()
    }

    fun fetchCreators() {
        if (state.isFetching) return
        fetch()
        state = state.copy(isFetching = true)
    }

    private fun getList() = viewModelScope.launch(Dispatchers.IO) {
        getCreatorsListUC(limit = 40).collectLatest { response ->
            when (response) {
                is ApiResponse.Error -> {
                    setLoading(false)
                }

                is ApiResponse.Loading -> {
                    setLoading(true)
                }

                is ApiResponse.Success -> {
                    val creators: List<Creator> =
                        response.data.data.results.map { it.toDomain() }
                    state.creatorList.addAll(creators)
                    setLoading(false)
                }
            }
        }
    }

    private fun fetch() = viewModelScope.launch(Dispatchers.IO) {
        state = state.copy(isFetching = true)
        getCreatorsListUC(offset = state.creatorList.size).collectLatest { response ->
            when (response) {
                is ApiResponse.Error -> {
                    state = state.copy(isFetching = false)
                }

                is ApiResponse.Loading -> {
                    state = state.copy(isFetching = true)
                }

                is ApiResponse.Success -> {
                    val creators: List<Creator> =
                        response.data.data.results.map { it.toDomain() }
                    val newList: MutableList<Creator> = state.creatorList.toMutableList()
                    newList.addAll(creators)
                    state = state.copy(
                        creatorList = newList,
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