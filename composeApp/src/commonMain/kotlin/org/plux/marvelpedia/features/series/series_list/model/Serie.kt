package org.plux.marvelpedia.features.series.series_list.model

import org.plux.marvelpedia.features.series.series_list.data.SeriesResponse

data class Serie(
    val title: String,
    val image: String = "",
)

fun SeriesResponse.toDomain(): Serie {
    return Serie(
        title = this.title,
        image = this.thumbnail.getURL()
    )
}