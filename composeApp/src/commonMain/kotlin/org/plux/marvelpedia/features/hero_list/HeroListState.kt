package org.plux.marvelpedia.features.hero_list

import org.plux.marvelpedia.features.hero_list.model.Hero

data class HeroListState(
    val heroList: List<Hero> = emptyList(),
    var isLoading: Boolean = true,
    var isFetching: Boolean = false,
)