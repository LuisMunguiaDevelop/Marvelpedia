package org.plux.marvelpedia

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.plux.marvelpedia.features.character_list.CharacterListScreen


@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(
            screen = CharacterListScreen(),
        ){ navigator ->
            SlideTransition(navigator)
        }
    }
}





