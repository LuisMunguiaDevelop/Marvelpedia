package org.plux.marvelpedia.features.characters.character_list.data.use_cases.get_character_list

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.plux.marvelpedia.network.ApiResponse
import org.plux.marvelpedia.network.marvel.MarvelClient
import org.plux.marvelpedia.network.marvel.MarvelClient.MARVEL_API_CHARACTERS
import org.plux.marvelpedia.network.marvel.MarvelClient.marvelHttpClient
import org.plux.marvelpedia.network.marvel.MarvelParametersBuilder
import org.plux.marvelpedia.network.marvel.MarvelResponse

class GetCharacterListUC {

    private val apiUrl = MarvelClient.getUrl(MARVEL_API_CHARACTERS)

    suspend operator fun invoke(
        offset: Int = 0,
        nameFilter: String = "",
        limit: Int = 20
    ): Flow<ApiResponse<MarvelResponse<CharacterResponse>>> =
        flow {
            emit(ApiResponse.Loading())

            try {
                val call = marvelHttpClient.get(apiUrl) {
                    url {
                        parameters.appendAll(
                            MarvelParametersBuilder()
                                .setOffset(offset)
                                .setNameFilter(nameFilter)
                                .setLimit(limit)
                                .build()
                        )
                    }
                }

                if (call.status.isSuccess()) {
                    val response = call.body<MarvelResponse<CharacterResponse>>()
                    emit(ApiResponse.Success(data = response))
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(message = e))
            }
        }
}