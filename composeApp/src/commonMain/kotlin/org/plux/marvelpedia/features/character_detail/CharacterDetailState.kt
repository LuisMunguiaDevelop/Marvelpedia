package org.plux.marvelpedia.features.character_detail

import org.plux.marvelpedia.features.character_list.model.Character

data class CharacterDetailState(
    val character: Character = Character()
)