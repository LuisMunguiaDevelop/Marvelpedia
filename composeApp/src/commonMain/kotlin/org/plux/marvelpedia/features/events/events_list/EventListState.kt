package org.plux.marvelpedia.features.events.events_list

import org.plux.marvelpedia.features.events.events_list.model.Event

data class EventListState(
    val eventList: MutableList<Event> = mutableListOf(),
    var isLoading: Boolean = true,
    var isFetching: Boolean = false,
)