package org.plux.marvelpedia.network.marvel

import io.ktor.http.ParametersBuilder
import io.ktor.http.Parameters
import org.plux.marvelpedia.BuildKonfig

class MarvelParametersBuilder {
    private val credentials = MarvelCredentialsBuilder.getCredentials()
    private val parameters = ParametersBuilder()
    private var offset: Int = 0
    private var nameFilter: String = ""

    fun build(): Parameters {
        parameters.append(MarvelParameters.APIKEY, BuildKonfig.MARVEL_PUBLIC_KEY)
        parameters.append(MarvelParameters.TS, credentials.timeStamp)
        parameters.append(MarvelParameters.HASH, credentials.hash)
        if (offset != 0) parameters.append(MarvelParameters.OFFSET, offset.toString())
        if(nameFilter.isNotBlank()) parameters.append(MarvelParameters.NAME_FILTER, nameFilter)
        return parameters.build()
    }

    fun setOffset(offset: Int): MarvelParametersBuilder {
        this.offset = offset
        return this
    }

    fun setNameFilter(nameFilter: String): MarvelParametersBuilder {
        this.nameFilter = nameFilter
        return this
    }
}