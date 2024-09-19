package org.plux.marvelpedia.commons.data

import kotlinx.serialization.Serializable
import org.plux.marvelpedia.network.marvel.MarvelClient

@Serializable
data class ThumbNailResponse(
    val path: String = "",
    val extension: String = ""
){
    fun getURL(): String{
        return MarvelClient.replaceHttpWithHttps("${path}.${extension}")
    }
}