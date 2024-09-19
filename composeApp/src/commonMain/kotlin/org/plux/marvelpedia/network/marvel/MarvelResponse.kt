package org.plux.marvelpedia.network.marvel

import kotlinx.serialization.Serializable

@Serializable
data class MarvelResponse<T>(
    val code: Int = 0,
    val status: String = "",
    val data: MarvelData<T>,
    val etag: String
)

@Serializable
data class MarvelData<T>(
    val offset: Int = 0,
    val limit: Int = 0,
    val total: Int = 0,
    val count: Int = 0,
    val results: List<T> = emptyList()
)