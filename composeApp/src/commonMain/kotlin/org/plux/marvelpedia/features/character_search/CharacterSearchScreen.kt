package org.plux.marvelpedia.features.character_search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.viewmodel.koinViewModel
import org.plux.marvelpedia.commons.ui.SearchBarComponent
import org.plux.marvelpedia.features.character_list.CharacterLazyList
import org.plux.marvelpedia.theme.primaryColor

class CharacterSearchScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<CharacterSearchViewModel>()
        val uiState by lazy { viewModel.state }

        CharacterSearchContent(
            uiState = uiState,
            //fetchList = { },
            onCharacterSearch = { viewModel.getList(it) },
            onBackPressed = { viewModel.onBackPressed() }
        )
    }

}

@Composable
fun CharacterSearchContent(
    uiState: CharacterSearchState,
    //fetchList: () -> Unit = {},
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
        CharacterLazyList(
            characterList = uiState.list,
            /*getScrollType = {
                when (it) {
                    ScrollingTypes.SCROLL_UP -> showSearchBar.value = true
                    ScrollingTypes.SCROLL_DOWN -> showSearchBar.value = false
                    ScrollingTypes.NONE -> showSearchBar.value = true
                    ScrollingTypes.FETCH -> {}
                    ScrollingTypes.END_REACHED -> {}
                }
            },*/
            /*onEndReached = {
                if (!uiState.isFetching) fetchList.invoke()
            },*/
        )

        //End of Scaffold content
    }
}

