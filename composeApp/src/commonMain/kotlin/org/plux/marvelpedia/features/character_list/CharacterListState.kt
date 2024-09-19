package org.plux.marvelpedia.features.character_list

import org.plux.marvelpedia.features.character_list.model.Character

data class CharacterListState(
    val characterList: List<Character> = emptyList(),
    var isLoading: Boolean = true,
    var isFetching: Boolean = false,
)