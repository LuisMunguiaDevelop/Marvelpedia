package org.plux.marvelpedia.features.hero_list.data.use_cases.get_hero_list

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.plux.marvelpedia.network.ApiResponse
import org.plux.marvelpedia.network.marvel.MarvelClient
import org.plux.marvelpedia.network.marvel.MarvelClient.MARVEL_API_CHARACTERS
import org.plux.marvelpedia.network.marvel.MarvelClient.getMarvelRequestParams
import org.plux.marvelpedia.network.marvel.MarvelClient.marvelHttpClient
import org.plux.marvelpedia.network.marvel.MarvelResponse

class GetHeroListUC {

    private val apiUrl = MarvelClient.getUrl(MARVEL_API_CHARACTERS)

    suspend operator fun invoke(offset: Int = 0): Flow<ApiResponse<MarvelResponse<HeroResponse>>> = flow  {
        emit(ApiResponse.Loading())


        try {
            val call = marvelHttpClient.get(apiUrl) {
                url {
                    parameters.appendAll(getMarvelRequestParams())
                    if(offset != 0) parameters.append("offset", offset.toString())
                }
            }

            if(call.status.isSuccess()){
                val response = call.body<MarvelResponse<HeroResponse>>()
                emit(ApiResponse.Success(data = response))
            }

        } catch (e: Exception) {
            emit(ApiResponse.Error(message = e))
        }
    }
}