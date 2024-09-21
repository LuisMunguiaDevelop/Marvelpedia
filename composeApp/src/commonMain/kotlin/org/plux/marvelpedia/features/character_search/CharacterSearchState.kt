package org.plux.marvelpedia.features.character_search

import org.plux.marvelpedia.commons.model.FilterableState
import org.plux.marvelpedia.features.character_list.model.Character

data class CharacterSearchState(
    override val list: List<Character> = listOf(),
    override var nameFilter: String = "",
    var isLoading: Boolean = false,
    var isFetching: Boolean = false,
    var hasActualItems: Boolean = false,
): FilterableState<Character>