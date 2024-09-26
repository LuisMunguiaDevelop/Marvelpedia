package org.plux.marvelpedia.features.comics.comic_list

import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.viewmodel.koinViewModel
import org.plux.marvelpedia.commons.ui.CollectionMainContent
import org.plux.marvelpedia.features.comics.comic_list.ui.ComicItem
import org.plux.marvelpedia.features.comics.comic_search.ComicSearchScreen

class ComicListScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<ComicListViewModel>()
        val uiState by lazy { viewModel.state }
        val navigator = LocalNavigator.currentOrThrow

        CollectionMainContent(
            title = "Comic List",
            content = {
                items(uiState.comicList) { comic ->
                    ComicItem(
                        comic = comic,
                        onClick = {
                            println(comic)
                        }
                    )
                }
            },
            isLoading = uiState.isLoading,
            isFetching = uiState.isFetching,
            fetchDetected = { viewModel.fetchComics() },
            onBackPressed = { navigator.pop() },
            onSearchPressed = { navigator.push(ComicSearchScreen()) }
        )
    }
}
