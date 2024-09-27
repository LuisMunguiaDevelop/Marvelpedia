package org.plux.marvelpedia.features.characters.character_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.plux.marvelpedia.features.characters.character_detail.data.GetComicsByCharacterUC
import org.plux.marvelpedia.features.characters.character_detail.data.GetEventsByCharacterUC
import org.plux.marvelpedia.features.characters.character_list.model.Character
import org.plux.marvelpedia.features.comics.comic_list.model.Comic
import org.plux.marvelpedia.features.comics.comic_list.model.toDomain
import org.plux.marvelpedia.features.events.events_list.model.Event
import org.plux.marvelpedia.features.events.events_list.model.toDomain
import org.plux.marvelpedia.network.ApiResponse

class CharacterDetailViewModel(
    character: Character,
    private val getComicListUC: GetComicsByCharacterUC,
    private val getEventListUC: GetEventsByCharacterUC
) : ViewModel(){
    var state by mutableStateOf(CharacterDetailState(character = character))
        private set

    init {
        getComics(characterId = character.id.toString())
        getEvents(characterId = character.id.toString())
    }

    private fun getComics(characterId: String) = viewModelScope.launch(Dispatchers.IO) {
        getComicListUC(characterId = characterId,limit = 40).collectLatest { response ->
            when (response) {
                is ApiResponse.Error -> {
                    state.isLoadingComics = false
                }

                is ApiResponse.Loading -> {
                    state.isLoadingComics = true
                }

                is ApiResponse.Success -> {
                    val comics: List<Comic> =
                        response.data.data.results.map { it.toDomain() }
                    state = state.copy(
                        comicList = comics,
                        isLoadingComics = false
                    )
                }
            }
        }
    }
    private fun getEvents(characterId: String) = viewModelScope.launch(Dispatchers.IO) {
        getEventListUC(characterId = characterId, limit = 40).collectLatest { response ->
            when (response) {
                is ApiResponse.Error -> {
                    state.isLoadingEvents = false
                }

                is ApiResponse.Loading -> {
                    state.isLoadingEvents = true
                }

                is ApiResponse.Success -> {
                    val events: List<Event> =
                        response.data.data.results.map { it.toDomain() }
                    state = state.copy(
                        eventList = events,
                        isLoadingEvents = false
                    )
                }
            }
        }
    }
}