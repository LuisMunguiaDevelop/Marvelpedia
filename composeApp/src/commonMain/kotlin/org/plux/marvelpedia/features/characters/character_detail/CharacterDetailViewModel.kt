package org.plux.marvelpedia.features.characters.character_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.plux.marvelpedia.features.characters.character_list.model.Character

class CharacterDetailViewModel : ViewModel(){
    var state by mutableStateOf(CharacterDetailState())
        private set

    fun setCharacter(character: Character){
        state = state.copy(character = character)
    }
}