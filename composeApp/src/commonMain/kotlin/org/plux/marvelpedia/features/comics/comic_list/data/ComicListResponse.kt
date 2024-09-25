package org.plux.marvelpedia.features.comics.comic_list.data

import kotlinx.serialization.Serializable
import org.plux.marvelpedia.commons.data.ThumbNailResponse

@Serializable
data class ComicResponse(
    val id: Int = 0,
    val title: String = "",
    val thumbnail: ThumbNailResponse = ThumbNailResponse(),
)