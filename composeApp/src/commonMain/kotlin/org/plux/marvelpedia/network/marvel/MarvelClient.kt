package org.plux.marvelpedia.network.marvel

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.ParametersBuilder
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.plux.marvelpedia.BuildKonfig

object MarvelClient {
    private const val MARVEL_API = "https://gateway.marvel.com/"
    const val MARVEL_API_CHARACTERS = "v1/public/characters?"

    val marvelHttpClient = HttpClient {
        install(ContentNegotiation){
            json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
        }
    }

    fun getUrl(action: String): String{
        return MARVEL_API.plus(action)
    }

    fun getMarvelRequestParams(): Parameters {
        val credentials = MarvelCredentialsBuilder.getCredentials()
        val parameters = ParametersBuilder()
        parameters.append(MarvelParameters.APIKEY, BuildKonfig.MARVEL_PUBLIC_KEY)
        parameters.append(MarvelParameters.TS, credentials.timeStamp)
        parameters.append(MarvelParameters.HASH, credentials.hash)

        return parameters.build()
    }

    fun replaceHttpWithHttps(url: String): String {
        return url.replace("http://", "https://")
    }
}