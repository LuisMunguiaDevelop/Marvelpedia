package org.plux.marvelpedia.network.marvel

import io.ktor.http.ParametersBuilder
import io.ktor.http.Parameters
import org.plux.marvelpedia.BuildKonfig

class MarvelParametersBuilder {
    private val credentials = MarvelCredentialsBuilder.getCredentials()
    private val parameters = ParametersBuilder()
    private var offset: Int = 0
    private var nameFilter: String = ""
    private var titleFilter: String = ""
    private var limit: Int = 0

    fun build(): Parameters {
        parameters.append(MarvelParameters.APIKEY, BuildKonfig.MARVEL_PUBLIC_KEY)
        parameters.append(MarvelParameters.TS, credentials.timeStamp)
        parameters.append(MarvelParameters.HASH, credentials.hash)
        if (offset != 0) parameters.append(MarvelParameters.OFFSET, offset.toString())
        if (nameFilter.isNotBlank()) parameters.append(MarvelParameters.NAME_FILTER, nameFilter)
        if (titleFilter.isNotBlank()) parameters.append(MarvelParameters.TITLE_FILTER, titleFilter)
        if (limit != 0) parameters.append(MarvelParameters.LIMIT, limit.toString())
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

    fun setTitleFilter(title: String): MarvelParametersBuilder {
        this.titleFilter = title
        return this
    }

    fun setLimit(limit: Int): MarvelParametersBuilder {
        this.limit = limit
        return this
    }
}