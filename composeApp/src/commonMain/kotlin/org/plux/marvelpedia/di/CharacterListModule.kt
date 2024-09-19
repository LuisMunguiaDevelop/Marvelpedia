package org.plux.marvelpedia.di

import org.koin.dsl.module
import org.plux.marvelpedia.features.character_list.data.use_cases.get_character_list.GetCharacterListUC

val characterListModule = module {
    factory { GetCharacterListUC() }
}