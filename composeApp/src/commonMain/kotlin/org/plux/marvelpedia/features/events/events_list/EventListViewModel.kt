package org.plux.marvelpedia.features.events.events_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.plux.marvelpedia.features.events.events_list.data.GetEventListUC
import org.plux.marvelpedia.features.events.events_list.model.Event
import org.plux.marvelpedia.features.events.events_list.model.toDomain
import org.plux.marvelpedia.network.ApiResponse

class EventListViewModel(
    private val getEventListUC: GetEventListUC
) : ViewModel() {
    var state by mutableStateOf(EventListState())
        private set

    init {
        getList()
    }

    fun fetchEvents() {
        if (state.isFetching) return
        fetch()
        state = state.copy(isFetching = true)
    }

    private fun getList() = viewModelScope.launch(Dispatchers.IO) {
        getEventListUC(limit = 40).collectLatest { response ->
            when (response) {
                is ApiResponse.Error -> {
                    setLoading(false)
                }

                is ApiResponse.Loading -> {
                    setLoading(true)
                }

                is ApiResponse.Success -> {
                    val events: List<Event> =
                        response.data.data.results.map { it.toDomain() }
                    state.eventList.addAll(events)
                    setLoading(false)
                }
            }
        }
    }

    private fun fetch() = viewModelScope.launch(Dispatchers.IO) {
        state = state.copy(isFetching = true)
        getEventListUC(offset = state.eventList.size).collectLatest { response ->
            when (response) {
                is ApiResponse.Error -> {
                    state = state.copy(isFetching = false)
                }

                is ApiResponse.Loading -> {
                    state = state.copy(isFetching = true)
                }

                is ApiResponse.Success -> {
                    val events: List<Event> =
                        response.data.data.results.map { it.toDomain() }
                    val newList: MutableList<Event> = state.eventList.toMutableList()
                    newList.addAll(events)
                    state = state.copy(
                        eventList = newList,
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