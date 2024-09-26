package org.plux.marvelpedia.features.creators.creators_list.data

import kotlinx.serialization.Serializable
import org.plux.marvelpedia.commons.data.ThumbNailResponse

@Serializable
data class CreatorsResponse(
    val id: Int = 0,
    val fullName: String = "",
    val thumbnail: ThumbNailResponse = ThumbNailResponse(),
)