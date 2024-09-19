package org.plux.marvelpedia

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.plux.marvelpedia.features.hero_list.HeroListScreen


@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(HeroListScreen())
    }
}





