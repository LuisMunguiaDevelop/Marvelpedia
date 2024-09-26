package org.plux.marvelpedia.features.creators.creators_list

import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.viewmodel.koinViewModel
import org.plux.marvelpedia.commons.ui.CollectionMainContent
import org.plux.marvelpedia.features.creators.creators_list.ui.CreatorItem

class CreatorsListScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<CreatorsListViewModel>()
        val uiState by lazy { viewModel.state }
        val navigator = LocalNavigator.currentOrThrow

        CollectionMainContent(
            title = "Creators List",
            content = {
                items(uiState.creatorList) { creator ->
                    CreatorItem(
                        creator = creator,
                        onClick = {
                            println(creator)
                        }
                    )
                }
            },
            isLoading = uiState.isLoading,
            isFetching = uiState.isFetching,
            fetchDetected = { viewModel.fetchCreators() },
            onBackPressed = { navigator.pop() },
            onSearchPressed = { }
        )
    }
}