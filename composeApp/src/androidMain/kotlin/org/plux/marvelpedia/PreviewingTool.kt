package org.plux.marvelpedia

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.plux.marvelpedia.features.hero_list.HeroListContent
import org.plux.marvelpedia.features.hero_list.HeroListState
import org.plux.marvelpedia.features.hero_list.model.Hero

@Preview(showSystemUi = true)
@Composable
fun PreviewHeroList(){
    HeroListContent(uiState = HeroListState(
        isLoading = false,
        heroList = listOf(
            Hero(name = "eaea", mainImage = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
            Hero(name = "eaea", mainImage = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
            Hero(name = "eaea", mainImage = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
        )
    ))
}