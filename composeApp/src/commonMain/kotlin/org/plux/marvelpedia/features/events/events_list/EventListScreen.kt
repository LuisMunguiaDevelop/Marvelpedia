package org.plux.marvelpedia.features.events.events_list

import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.viewmodel.koinViewModel
import org.plux.marvelpedia.commons.ui.CollectionMainContent
import org.plux.marvelpedia.features.events.events_list.ui.EventItem
import org.plux.marvelpedia.features.events.events_search.EventsSearchScreen

class EventListScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<EventListViewModel>()
        val uiState by lazy { viewModel.state }
        val navigator = LocalNavigator.currentOrThrow

        CollectionMainContent(
            title = "Events List",
            content = {
                items(uiState.eventList) { event ->
                    EventItem(
                        event = event,
                        onClick = {
                            println(event)
                        }
                    )
                }
            },
            isLoading = uiState.isLoading,
            isFetching = uiState.isFetching,
            fetchDetected = { viewModel.fetchEvents() },
            onBackPressed = { navigator.pop() },
            onSearchPressed = { navigator.push(EventsSearchScreen()) }
        )
    }
}