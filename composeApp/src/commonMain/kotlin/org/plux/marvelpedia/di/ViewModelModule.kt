package org.plux.marvelpedia.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.plux.marvelpedia.features.characters.character_detail.CharacterDetailViewModel
import org.plux.marvelpedia.features.characters.character_list.CharacterListViewModel
import org.plux.marvelpedia.features.characters.character_search.CharacterSearchViewModel
import org.plux.marvelpedia.features.comics.comic_list.ComicListViewModel

val viewModelModule = module {
    viewModel { CharacterListViewModel(get()) }
    viewModel { CharacterDetailViewModel() }
    viewModel { CharacterSearchViewModel(get()) }
    viewModel { ComicListViewModel(get()) }
}