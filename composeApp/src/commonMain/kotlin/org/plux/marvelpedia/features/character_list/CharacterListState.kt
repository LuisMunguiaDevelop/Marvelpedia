package org.plux.marvelpedia.features.character_list

import org.plux.marvelpedia.features.character_list.model.Character

data class CharacterListState(
    val characterList: MutableList<Character> = mutableListOf(),
    val filteredCharacterList: MutableList<Character> = mutableListOf(),
    var isLoading: Boolean = true,
    var isFetching: Boolean = false,
)