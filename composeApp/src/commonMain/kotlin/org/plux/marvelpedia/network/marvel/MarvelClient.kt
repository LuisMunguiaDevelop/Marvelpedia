package org.plux.marvelpedia.network.marvel

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object MarvelClient {
    private const val MARVEL_API = "https://gateway.marvel.com/"
    const val MARVEL_API_CHARACTERS = "v1/public/characters?"
    const val MARVEL_API_COMICS = "v1/public/comics?"
    const val MARVEL_API_SERIES = "v1/public/series?"
    const val MARVEL_API_EVENTS = "v1/public/events?"
    const val MARVEL_API_CREATORS = "v1/public/creators?"

    val marvelHttpClient = HttpClient {
        install(ContentNegotiation) {
            json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
        }
    }

    fun getUrl(action: String): String {
        return MARVEL_API.plus(action)
    }

    fun replaceHttpWithHttps(url: String): String {
        return url.replace("http://", "https://")
    }
}