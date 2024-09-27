package org.plux.marvelpedia.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.plux.marvelpedia.features.characters.character_detail.CharacterDetailViewModel
import org.plux.marvelpedia.features.characters.character_detail.data.GetComicsByCharacterUC
import org.plux.marvelpedia.features.characters.character_detail.data.GetEventsByCharacterUC
import org.plux.marvelpedia.features.characters.character_list.CharacterListViewModel
import org.plux.marvelpedia.features.characters.character_list.data.use_cases.get_character_list.GetCharacterListUC
import org.plux.marvelpedia.features.characters.character_search.CharacterSearchViewModel

val charactersModule = module {
    viewModel { CharacterListViewModel(get()) }
    viewModel { parameters-> CharacterDetailViewModel(character = parameters.get(), get(), get()) }
    viewModel { CharacterSearchViewModel(get()) }

    factory { GetCharacterListUC() }
    factory { GetComicsByCharacterUC() }
    factory { GetEventsByCharacterUC() }
}