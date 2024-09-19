package org.plux.marvelpedia.features.hero_list.model

import org.plux.marvelpedia.features.hero_list.data.use_cases.get_hero_list.HeroResponse


data class Hero(
    val name: String,
    val mainImage: String,
)

fun HeroResponse.toDomain(): Hero{
    return Hero(
        name = this.name,
        mainImage = this.thumbnail.getURL()
    )
}