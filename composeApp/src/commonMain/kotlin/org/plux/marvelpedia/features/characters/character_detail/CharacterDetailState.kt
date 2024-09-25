package org.plux.marvelpedia.features.characters.character_detail

import org.plux.marvelpedia.features.characters.character_list.model.Character

data class CharacterDetailState(
    val character: Character = Character()
)