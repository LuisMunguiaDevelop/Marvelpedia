package org.plux.marvelpedia.features.series.series_list

import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.viewmodel.koinViewModel
import org.plux.marvelpedia.commons.ui.CollectionMainContent
import org.plux.marvelpedia.features.series.series_list.ui.SerieItem

class SeriesListScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SeriesListViewModel>()
        val uiState by lazy { viewModel.state }
        val navigator = LocalNavigator.currentOrThrow

        CollectionMainContent(
            title = "Series List",
            content = {
                items(uiState.seriesList) { serie ->
                    SerieItem(
                        serie = serie,
                        onClick = {
                            println(serie)
                        }
                    )
                }
            },
            isLoading = uiState.isLoading,
            isFetching = uiState.isFetching,
            fetchDetected = { viewModel.fetchSeries() },
            onBackPressed = { navigator.pop() },
            onSearchPressed = { }
        )
    }
}