package org.plux.marvelpedia.features.creators.creators_list

import org.plux.marvelpedia.features.creators.creators_list.model.Creator

data class CreatorsListState(
    val creatorList: MutableList<Creator> = mutableListOf(),
    var isLoading: Boolean = true,
    var isFetching: Boolean = false,
)