package org.plux.marvelpedia.features.creators.creators_list.model

import org.plux.marvelpedia.features.creators.creators_list.data.CreatorsResponse

data class Creator(
    val title: String,
    val image: String = "",
)

fun CreatorsResponse.toDomain(): Creator {
    return Creator(
        title = this.fullName,
        image = this.thumbnail.getURL()
    )
}