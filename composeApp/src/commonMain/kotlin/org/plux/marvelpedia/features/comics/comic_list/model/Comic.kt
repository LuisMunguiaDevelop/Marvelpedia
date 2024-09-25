package org.plux.marvelpedia.features.comics.comic_list.model

import org.plux.marvelpedia.features.comics.comic_list.data.ComicResponse


data class Comic(
    val title: String,
    val image: String = "",
)

fun ComicResponse.toDomain(): Comic{
    return Comic(
        title = this.title,
        image = this.thumbnail.getURL()
    )
}