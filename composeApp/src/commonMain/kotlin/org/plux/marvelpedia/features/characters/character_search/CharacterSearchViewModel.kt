package org.plux.marvelpedia.features.characters.character_search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.plux.marvelpedia.commons.model.SearchScreenState
import org.plux.marvelpedia.commons.model.handleFilteredList
import org.plux.marvelpedia.features.characters.character_list.data.use_cases.get_character_list.GetCharacterListUC
import org.plux.marvelpedia.features.characters.character_list.model.Character
import org.plux.marvelpedia.features.characters.character_list.model.toDomain
import org.plux.marvelpedia.network.ApiResponse

class CharacterSearchViewModel(
    private val getCharacterListUC: GetCharacterListUC
) : ViewModel() {
    var state by mutableStateOf(SearchScreenState<Character>())
        private set

    fun getList(nameFilter: String = "") {
        handleFilteredList(
            state = state,
            nameFilter = nameFilter,
            fetchList = { getFilteredList(state.nameFilter) },
            clearList = { state = state.copy(list = emptyList())}
        )
    }

    fun onBackPressed(){
        state = SearchScreenState()
    }

    private fun getFilteredList(nameFilter: String = "") = viewModelScope.launch(Dispatchers.IO) {
        getCharacterListUC(nameFilter = nameFilter).collectLatest { response ->
            when (response) {
                is ApiResponse.Error -> {
                }

                is ApiResponse.Loading -> {
                    state = state.copy(isLoading = true)
                }

                is ApiResponse.Success -> {
                    val characters: List<Character> =
                        response.data.data.results.map { it.toDomain() }
                    state = state.copy(list = characters, isLoading = false)
                }
            }
        }
    }

}