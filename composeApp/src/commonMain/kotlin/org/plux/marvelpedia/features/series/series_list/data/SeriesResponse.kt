package org.plux.marvelpedia.features.series.series_list.data

import kotlinx.serialization.Serializable
import org.plux.marvelpedia.commons.data.ThumbNailResponse

@Serializable
data class SeriesResponse(
    val id: Int = 0,
    val title: String = "",
    val thumbnail: ThumbNailResponse = ThumbNailResponse(),
)