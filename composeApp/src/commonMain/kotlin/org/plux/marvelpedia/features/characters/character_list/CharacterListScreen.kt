package org.plux.marvelpedia.features.characters.character_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
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
import org.plux.marvelpedia.commons.ui.CollectionMainContent
import org.plux.marvelpedia.commons.ui.SectionButton
import org.plux.marvelpedia.features.characters.character_detail.CharacterDetailScreen
import org.plux.marvelpedia.features.characters.character_list.ui.CharacterItem
import org.plux.marvelpedia.features.characters.character_search.CharacterSearchScreen
import org.plux.marvelpedia.features.comics.comic_list.ComicListScreen
import org.plux.marvelpedia.features.events.events_list.EventListScreen
import org.plux.marvelpedia.features.series.series_list.SeriesListScreen

class CharacterListScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<CharacterListViewModel>()
        val uiState by lazy { viewModel.state }
        val navigator = LocalNavigator.currentOrThrow

        CollectionMainContent(
            content = {
                items(uiState.characterList) { character ->
                    CharacterItem(
                        character = character,
                        onClick = { navigator.push(CharacterDetailScreen(character = it)) }
                    )
                }
            },
            isLoading = uiState.isLoading,
            isFetching = uiState.isFetching,
            fetchDetected = { viewModel.fetchCharacters() },
            topBar = {
                CharacterListTopBar(
                    onSearchPressed = { navigator.push(CharacterSearchScreen()) },
                    onGoToComicsPressed = { navigator.push(ComicListScreen()) },
                    onGoToSeriesPressed = { navigator.push(SeriesListScreen()) },
                    onGoToEventsPressed = { navigator.push(EventListScreen()) }
                )
            }
        )
    }
}


@Composable
fun CharacterListTopBar(
    onSearchPressed: () -> Unit,
    onGoToComicsPressed: () -> Unit,
    onGoToSeriesPressed: () -> Unit,
    onGoToEventsPressed: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            SectionButton(
                buttonTitle = "Comics",
                onClick = onGoToComicsPressed
            )

            SectionButton(
                buttonTitle = "Series",
                onClick = onGoToSeriesPressed
            )

            SectionButton(
                buttonTitle = "Events",
                onClick = onGoToEventsPressed
            )
        }

        Image(
            painter = painterResource(Res.drawable.search_icon),
            contentDescription = "search character",
            modifier = Modifier
                .clickable { onSearchPressed.invoke() }
        )
    }
}




