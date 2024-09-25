package org.plux.marvelpedia.features.characters.character_search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.viewmodel.koinViewModel
import org.plux.marvelpedia.commons.ui.SearchBarComponent
import org.plux.marvelpedia.features.characters.character_detail.CharacterDetailScreen
import org.plux.marvelpedia.features.characters.character_list.ui.CharacterItem
import org.plux.marvelpedia.theme.primaryColor

class CharacterSearchScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<CharacterSearchViewModel>()
        val uiState by lazy { viewModel.state }

        CharacterSearchContent(
            uiState = uiState,
            onCharacterSearch = { viewModel.getList(it) },
            onBackPressed = { viewModel.onBackPressed() }
        )
    }

}

@Composable
fun CharacterSearchContent(
    uiState: CharacterSearchState,
    onCharacterSearch: (String) -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    val navigator = LocalNavigator.currentOrThrow
    val showSearchBar = remember { mutableStateOf(true) }

    Scaffold(
        backgroundColor = primaryColor,
        topBar = {
            if (showSearchBar.value) {
                SearchBarComponent(
                    placeHolderString = "Search a character",
                    onTyped = {
                        onCharacterSearch.invoke(it)
                    },
                    isLoading = uiState.isLoading,
                    onBackPressed = {
                        navigator.pop()
                        onBackPressed.invoke()
                    },
                    initialText = uiState.nameFilter
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {

        //Scaffold content

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier
                .background(color = primaryColor)
                .fillMaxSize(),

            ) {
            items(uiState.list) { character ->
                CharacterItem(
                    character = character,
                    onClick = { navigator.push(CharacterDetailScreen(character = it)) }
                )
            }

            //End of Scaffold content
        }
    }
}

