package org.plux.marvelpedia.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.plux.marvelpedia.features.character_detail.CharacterDetailViewModel
import org.plux.marvelpedia.features.character_list.CharacterListViewModel

val viewModelModule = module {
    viewModel { CharacterListViewModel( get() ) }
    viewModel { CharacterDetailViewModel() }
}