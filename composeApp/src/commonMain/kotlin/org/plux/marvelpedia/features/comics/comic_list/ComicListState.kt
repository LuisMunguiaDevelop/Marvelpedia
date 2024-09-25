package org.plux.marvelpedia.features.comics.comic_list

import org.plux.marvelpedia.features.comics.comic_list.model.Comic

data class ComicListState(
    val comicList: MutableList<Comic> = mutableListOf(),
    var isLoading: Boolean = true,
    var isFetching: Boolean = false,
)