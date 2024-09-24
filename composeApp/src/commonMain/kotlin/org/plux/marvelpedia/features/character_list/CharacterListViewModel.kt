package org.plux.marvelpedia.features.character_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.plux.marvelpedia.features.character_list.data.use_cases.get_character_list.GetCharacterListUC
import org.plux.marvelpedia.features.character_list.model.Character
import org.plux.marvelpedia.features.character_list.model.toDomain
import org.plux.marvelpedia.network.ApiResponse

class CharacterListViewModel(
    private val getCharacterListUC: GetCharacterListUC
) : ViewModel() {
    var state by mutableStateOf(CharacterListState())
        private set


    init {
        getList()
    }

    fun fetchCharacters() {
        if (state.isFetching) return
        fetch()
        state = state.copy(isFetching = true)
    }

    private fun getList() = viewModelScope.launch(Dispatchers.IO) {
        getCharacterListUC(limit = 40).collectLatest { response ->
            when (response) {
                is ApiResponse.Error -> {
                    setLoading(false)
                }

                is ApiResponse.Loading -> {
                    setLoading(true)
                }

                is ApiResponse.Success -> {
                    val characters: List<Character> =
                        response.data.data.results.map { it.toDomain() }
                    state.characterList.addAll(characters)
                    setLoading(false)
                }
            }
        }
    }

    private fun fetch() = viewModelScope.launch(Dispatchers.IO) {
        state = state.copy(isFetching = true)
        getCharacterListUC(offset = state.characterList.size).collectLatest { response ->
            when (response) {
                is ApiResponse.Error -> {
                    state = state.copy(isFetching = false)
                }

                is ApiResponse.Loading -> {
                    state = state.copy(isFetching = true)
                }

                is ApiResponse.Success -> {
                    val characters: List<Character> =
                        response.data.data.results.map { it.toDomain() }
                    val newList: MutableList<Character> = state.characterList.toMutableList()
                    newList.addAll(characters)
                    state = state.copy(
                        characterList = newList,
                        isFetching = false
                    )
                }
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        state = state.copy(isLoading = loading)
    }

}