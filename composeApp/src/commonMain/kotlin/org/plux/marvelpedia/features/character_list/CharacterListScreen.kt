package org.plux.marvelpedia.features.character_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject
import org.plux.marvelpedia.commons.ui.LoadingComponent
import org.plux.marvelpedia.features.character_list.model.Character
import org.plux.marvelpedia.features.character_list.ui.CharacterItem
import org.plux.marvelpedia.theme.primaryColor

class CharacterListScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: CharacterListViewModel = koinInject()
        val uiState = viewModel.state

        CharacterListContent(
            uiState = uiState,
            fetchList = {
                viewModel.fetchCharacters()
            }
        )
    }

}

@Composable
fun CharacterListContent(
    uiState: CharacterListState,
    fetchList: () -> Unit = {}
) {
    val currentIndex = remember { mutableStateOf(0) }
    val listSize = remember { mutableStateOf(uiState.characterList.size) }

    LaunchedEffect(key1 = listSize, key2 = currentIndex.value) {
        if (
            currentIndex.value >= (listSize.value) - 5
            && currentIndex.value != 0
            && !uiState.isFetching
        ) {
            fetchList.invoke()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = primaryColor)
    ) {
        if (uiState.isLoading) {
            LoadingComponent()
        } else {
            CharacterLazyList(
                characterList = uiState.characterList,
                setCurrentIndex = {
                    currentIndex.value = it
                }
            )
        }
    }
}

@Composable
fun CharacterLazyList(
    characterList: List<Character>,
    setCurrentIndex: (Int) -> Unit
) {
    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(10.dp),
        state = listState,
        modifier = Modifier
            .background(color = primaryColor)
    ) {
        items(characterList) { character ->
            CharacterItem(character = character)
            val currentIndex = characterList.indexOf(character)
            LaunchedEffect(listState.firstVisibleItemIndex) {
                setCurrentIndex(currentIndex)
            }
        }
    }
}