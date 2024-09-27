package org.plux.marvelpedia.features.characters.character_detail.data

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.plux.marvelpedia.features.comics.comic_list.data.ComicResponse
import org.plux.marvelpedia.network.ApiResponse
import org.plux.marvelpedia.network.marvel.MarvelClient.marvelHttpClient
import org.plux.marvelpedia.network.marvel.MarvelParametersBuilder
import org.plux.marvelpedia.network.marvel.MarvelResponse

class GetComicsByCharacterUC {

    suspend operator fun invoke(
        offset: Int = 0,
        limit: Int = 20,
        characterId: String,
    ): Flow<ApiResponse<MarvelResponse<ComicResponse>>> =
        flow {
            val url = "https://gateway.marvel.com:443/v1/public/characters/$characterId/comics?"
            emit(ApiResponse.Loading())

            try {
                val call = marvelHttpClient.get(url) {
                    url {
                        parameters.appendAll(
                            MarvelParametersBuilder()
                                .setOffset(offset)
                                .setLimit(limit)
                                .build()
                        )
                    }
                }

                if (call.status.isSuccess()) {
                    val response = call.body<MarvelResponse<ComicResponse>>()
                    emit(ApiResponse.Success(data = response))
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(message = e))
            }
        }
}