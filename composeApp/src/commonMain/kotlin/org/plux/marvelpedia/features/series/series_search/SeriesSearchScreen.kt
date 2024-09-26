package org.plux.marvelpedia.features.series.series_search

import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.viewmodel.koinViewModel
import org.plux.marvelpedia.commons.ui.CollectionSearchScreen
import org.plux.marvelpedia.features.comics.comic_list.ui.ComicItem
import org.plux.marvelpedia.features.comics.comic_search.ComicSearchViewModel
import org.plux.marvelpedia.features.series.series_list.ui.SerieItem

class SeriesSearchScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SeriesSearchViewModel>()
        val uiState by lazy { viewModel.state }
        val navigator = LocalNavigator.currentOrThrow

        CollectionSearchScreen(
            isLoading = uiState.isLoading,
            initialText = uiState.nameFilter,
            placerHolderString = "Search a series",
            onTypedDetected = { viewModel.getList(it) },
            onBackPressed = {
                navigator.pop()
                viewModel.onBackPressed()
            },
            gridContent = {
                items(uiState.list) { series ->
                    SerieItem(
                        serie = series,
                        onClick = { }
                    )
                }
            }
        )
    }

}