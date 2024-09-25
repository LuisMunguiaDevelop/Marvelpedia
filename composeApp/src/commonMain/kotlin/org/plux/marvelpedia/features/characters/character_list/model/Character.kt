package org.plux.marvelpedia.features.characters.character_list.model

import org.plux.marvelpedia.features.characters.character_list.data.use_cases.get_character_list.CharacterResponse


data class Character(
    val name: String = "",
    val mainImage: String = "",
    val description: String = "",
)

fun CharacterResponse.toDomain(): Character {
    return Character(
        name = this.name,
        mainImage = this.thumbnail.getURL(),
        description = this.description ?: ""
    )
}