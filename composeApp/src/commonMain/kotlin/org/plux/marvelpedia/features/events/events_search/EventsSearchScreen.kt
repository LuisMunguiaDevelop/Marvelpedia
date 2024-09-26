package org.plux.marvelpedia.features.events.events_search

import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.viewmodel.koinViewModel
import org.plux.marvelpedia.commons.ui.CollectionSearchScreen
import org.plux.marvelpedia.features.events.events_list.ui.EventItem

class EventsSearchScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<EventsSearchViewModel>()
        val uiState by lazy { viewModel.state }
        val navigator = LocalNavigator.currentOrThrow

        CollectionSearchScreen(
            isLoading = uiState.isLoading,
            initialText = uiState.nameFilter,
            placerHolderString = "Search an event",
            onTypedDetected = { viewModel.getList(it) },
            onBackPressed = {
                navigator.pop()
                viewModel.onBackPressed()
            },
            gridContent = {
                items(uiState.list) { event ->
                    EventItem(
                        event = event,
                        onClick = { }
                    )
                }
            }
        )
    }

}