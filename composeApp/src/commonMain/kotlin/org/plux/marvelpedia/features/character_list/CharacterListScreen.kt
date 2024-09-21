package org.plux.marvelpedia.features.character_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import marvelpedia.composeapp.generated.resources.Res
import marvelpedia.composeapp.generated.resources.search_icon
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.plux.marvelpedia.commons.model.ScrollingTypes
import org.plux.marvelpedia.commons.ui.LoadingComponent
import org.plux.marvelpedia.features.character_detail.CharacterDetailScreen
import org.plux.marvelpedia.features.character_list.model.Character
import org.plux.marvelpedia.features.character_list.ui.CharacterItem
import org.plux.marvelpedia.features.character_search.CharacterSearchScreen
import org.plux.marvelpedia.theme.primaryColor

class CharacterListScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<CharacterListViewModel>()
        val uiState by lazy { viewModel.state }

        CharacterListContent(
            uiState = uiState,
            fetchList = {
                viewModel.fetchCharacters()
            },
        )
    }

}

@Composable
fun CharacterListContent(
    uiState: CharacterListState,
    fetchList: () -> Unit = {},
) {
    val showSearchBar = remember { mutableStateOf(true) }
    val navigator = LocalNavigator.currentOrThrow

    Scaffold(
        backgroundColor = primaryColor,
        topBar = {
            if(showSearchBar.value && !uiState.isLoading)
            CharacterListTopBar(
                onSearchPressed = { navigator.push(CharacterSearchScreen()) }
            )
        },
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(color = primaryColor)
                .padding(innerPadding)
        ) {
            if (uiState.isLoading) {
                LoadingComponent()
            } else {
                CharacterLazyList(
                    characterList = uiState.characterList,
                    getScrollType = {
                        when (it) {
                            ScrollingTypes.SCROLL_UP -> showSearchBar.value = true
                            ScrollingTypes.SCROLL_DOWN -> showSearchBar.value = false
                            ScrollingTypes.NONE -> showSearchBar.value = true
                        }
                    },
                    onEndReached = {
                        if (!uiState.isFetching) fetchList.invoke()
                    },
                )
            }
        }
    }
}


@Composable
fun CharacterLazyList(
    characterList: List<Character>,
    getScrollType: (ScrollingTypes) -> Unit = { },
    onEndReached: () -> Unit = {},
) {
    val listState = rememberLazyGridState()
    val navigator = LocalNavigator.currentOrThrow
    val previousIndex = remember { mutableStateOf(0) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        state = listState,
        modifier = Modifier
            .background(color = primaryColor)
            .fillMaxSize()
    ) {
        items(characterList) { character ->

            CharacterItem(
                character = character,
                onClick = { navigator.push(CharacterDetailScreen(character = it)) }
            )

            LaunchedEffect(listState.firstVisibleItemIndex) {

                if (listState.firstVisibleItemIndex > previousIndex.value) {
                    getScrollType.invoke(ScrollingTypes.SCROLL_DOWN)
                } else if (listState.firstVisibleItemIndex < previousIndex.value) {
                    getScrollType.invoke(ScrollingTypes.SCROLL_UP)
                }
                previousIndex.value = listState.firstVisibleItemIndex
            }
        }
    }


}

@Composable
fun CharacterListTopBar(
    onSearchPressed: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {

        Image(
            painter = painterResource(Res.drawable.search_icon),
            contentDescription = "search character",
            modifier = Modifier
                .clickable { onSearchPressed.invoke() }
        )
    }
}
