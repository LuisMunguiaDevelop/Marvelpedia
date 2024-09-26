package org.plux.marvelpedia.features.events.events_search

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
import org.plux.marvelpedia.features.events.events_list.data.GetEventListUC
import org.plux.marvelpedia.features.events.events_list.model.Event
import org.plux.marvelpedia.features.events.events_list.model.toDomain
import org.plux.marvelpedia.network.ApiResponse

class EventsSearchViewModel(
    private val getEventListUC: GetEventListUC
) : ViewModel() {
    var state by mutableStateOf(SearchScreenState<Event>())
        private set

    fun getList(nameFilter: String = "") {
        handleFilteredList(
            state = state,
            nameFilter = nameFilter,
            fetchList = { getFilteredList(state.nameFilter) },
            clearList = { state = state.copy(list = emptyList()) }
        )
    }

    fun onBackPressed() {
        state = SearchScreenState()
    }

    private fun getFilteredList(nameFilter: String = "") = viewModelScope.launch(Dispatchers.IO) {
        getEventListUC(nameFilter = nameFilter).collectLatest { response ->
            when (response) {
                is ApiResponse.Error -> {
                    println(response.data)
                }

                is ApiResponse.Loading -> {
                    state = state.copy(isLoading = true)
                }

                is ApiResponse.Success -> {
                    val events: List<Event> =
                        response.data.data.results.map { it.toDomain() }
                    state = state.copy(list = events, isLoading = false)
                }
            }
        }
    }
}