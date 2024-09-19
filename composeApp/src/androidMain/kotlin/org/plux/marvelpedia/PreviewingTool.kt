package org.plux.marvelpedia

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.plux.marvelpedia.features.character_list.CharacterListContent
import org.plux.marvelpedia.features.character_list.CharacterListState
import org.plux.marvelpedia.features.character_list.model.Character

@Preview(showSystemUi = true)
@Composable
fun PreviewHeroList(){
    CharacterListContent(uiState = CharacterListState(
        isLoading = false,
        characterList = listOf(
            Character(name = "eaea", mainImage = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
            Character(name = "eaea", mainImage = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
            Character(name = "eaea", mainImage = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
        )
    )
    )
}