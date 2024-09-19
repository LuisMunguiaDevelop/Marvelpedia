package org.plux.marvelpedia.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.plux.marvelpedia.features.character_list.CharacterListViewModel

val viewModelModule = module {
    viewModel { CharacterListViewModel( get() ) }
}