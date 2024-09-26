package org.plux.marvelpedia.features.characters.character_search

import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.viewmodel.koinViewModel
import org.plux.marvelpedia.commons.ui.CollectionSearchScreen
import org.plux.marvelpedia.features.characters.character_detail.CharacterDetailScreen
import org.plux.marvelpedia.features.characters.character_list.ui.CharacterItem

class CharacterSearchScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<CharacterSearchViewModel>()
        val uiState by lazy { viewModel.state }
        val navigator = LocalNavigator.currentOrThrow

        CollectionSearchScreen(
            isLoading = uiState.isLoading,
            initialText = uiState.nameFilter,
            placerHolderString = "Search a Character",
            onTypedDetected = { viewModel.getList(it) },
            onBackPressed = {
                navigator.pop()
                viewModel.onBackPressed()
            },
            gridContent = {
                items(uiState.list) { character ->
                    CharacterItem(
                        character = character,
                        onClick = { navigator.push(CharacterDetailScreen(character)) }
                    )
                }
            }
        )
    }
}

