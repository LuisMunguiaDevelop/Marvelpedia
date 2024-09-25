package org.plux.marvelpedia.features.characters.character_list.data.use_cases.get_character_list

import kotlinx.serialization.Serializable
import org.plux.marvelpedia.commons.data.ThumbNailResponse

@Serializable
data class CharacterResponse(
    val id: Int = 0,
    val name: String = "",
    val description: String? = "",
    val thumbnail: ThumbNailResponse = ThumbNailResponse(),
)

