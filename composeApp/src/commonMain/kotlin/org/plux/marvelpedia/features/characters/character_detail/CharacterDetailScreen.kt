package org.plux.marvelpedia.features.characters.character_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.internal.BackHandler
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.ParametersHolder
import org.plux.marvelpedia.commons.ui.LoadingComponent
import org.plux.marvelpedia.commons.ui.TopBarComponent
import org.plux.marvelpedia.features.characters.character_list.model.Character
import org.plux.marvelpedia.features.comics.comic_list.model.Comic
import org.plux.marvelpedia.features.comics.comic_list.ui.ComicItem
import org.plux.marvelpedia.features.events.events_list.model.Event
import org.plux.marvelpedia.features.events.events_list.ui.EventItem
import org.plux.marvelpedia.theme.Typography
import org.plux.marvelpedia.theme.primaryColor

class CharacterDetailScreen(val character: Character) : Screen {

    @Composable
    override fun Content() {

        val viewModel = koinViewModel<CharacterDetailViewModel>(
            parameters = { ParametersHolder().add(character) }
        )
        val uiState by lazy { viewModel.state }

        CharacterDetailContent(
            uiState = uiState
        )
    }
}

@OptIn(InternalVoyagerApi::class)
@Composable
fun CharacterDetailContent(
    uiState: CharacterDetailState
) {
    val navigator = LocalNavigator.currentOrThrow

    BackHandler(enabled = false, onBack = {})

    Scaffold(
        backgroundColor = primaryColor,
        topBar = {
            TopBarComponent(
                title = uiState.character.name,
                onBackPressed = { navigator.pop() }
            )
        },
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            //Photo
            AsyncImage(
                model = uiState.character.mainImage,
                contentDescription = uiState.character.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
            )


            Text(
                text = uiState.character.description.ifBlank { "This character does not have a description." },
                textAlign = TextAlign.Center,
                style = Typography.body1,
                modifier = Modifier.padding(top = 10.dp)
            )

            Spacer(Modifier.size(60.dp))

            if (!uiState.isLoadingComics && !uiState.isLoadingEvents) {
                CharacterComicList(
                    comicList = uiState.comicList
                )

                Spacer(Modifier.size(20.dp).fillMaxWidth())

                if (uiState.eventList.isNotEmpty()) {
                    CharacterEventList(
                        eventList = uiState.eventList
                    )
                }
            } else {
                LoadingComponent()
            }
        }
    }
}

@Composable
fun CharacterComicList(
    comicList: List<Comic>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Comics",
            style = Typography.subtitle1
        )

        LazyRow {
            items(comicList) { comic ->
                ComicItem(
                    comic = comic,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
fun CharacterEventList(
    eventList: List<Event>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Events",
            style = Typography.subtitle1
        )

        LazyRow {
            items(eventList) { event ->
                EventItem(
                    event = event,
                    onClick = {}
                )
            }
        }
    }
}

