package org.plux.marvelpedia.features.hero_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject
import org.plux.marvelpedia.commons.ui.LoadingComponent
import org.plux.marvelpedia.features.hero_list.model.Hero
import org.plux.marvelpedia.features.hero_list.ui.HeroItem
import org.plux.marvelpedia.theme.primaryColor

class HeroListScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: HeroListViewModel = koinInject()
        val uiState = viewModel.state

        HeroListContent(
            uiState = uiState,
        )
    }

}

@Composable
fun HeroListContent(
    uiState: HeroListState,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = primaryColor)
    ) {
        if (uiState.isLoading) {
            LoadingComponent()
        } else {
            HeroLazyList(heroList = uiState.heroList)
        }
    }
}

@Composable
fun HeroLazyList(
    heroList: List<Hero>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .background(color = primaryColor)
    ) {
        items(heroList) { hero ->
            HeroItem(hero = hero)
        }
    }
}