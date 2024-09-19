package org.plux.marvelpedia.features.hero_list.data.use_cases.get_hero_list

import kotlinx.serialization.Serializable
import org.plux.marvelpedia.commons.data.ThumbNailResponse

@Serializable
data class HeroResponse(
    val id: Int = 0,
    val name: String = "",
    val description: String? = "",
    val thumbnail: ThumbNailResponse = ThumbNailResponse(),
)

