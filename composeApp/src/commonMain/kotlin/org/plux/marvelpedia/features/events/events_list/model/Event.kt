package org.plux.marvelpedia.features.events.events_list.model

import org.plux.marvelpedia.features.events.events_list.data.EventsResponse

data class Event(
    val title: String,
    val image: String = "",
)

fun EventsResponse.toDomain(): Event {
    return Event(
        title = this.title,
        image = this.thumbnail.getURL()
    )
}