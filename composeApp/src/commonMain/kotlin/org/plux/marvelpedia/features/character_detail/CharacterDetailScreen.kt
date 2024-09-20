package org.plux.marvelpedia.features.character_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import org.koin.compose.koinInject
import org.plux.marvelpedia.commons.ui.TopBarComponent
import org.plux.marvelpedia.features.character_list.model.Character
import org.plux.marvelpedia.theme.Typography
import org.plux.marvelpedia.theme.primaryColor

class CharacterDetailScreen(val character: Character) : Screen {

    @Composable
    override fun Content() {
        val viewModel: CharacterDetailViewModel = koinInject()
        viewModel.setCharacter(character = character)
        val uiState = viewModel.state

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

    BackHandler(enabled = false, onBack = {} )

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
            //.windowInsetsPadding(WindowInsets.safeDrawing)
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                //.windowInsetsPadding(WindowInsets.statusBars)
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
        }
    }
}

