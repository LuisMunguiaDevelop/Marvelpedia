package org.plux.marvelpedia.di

import org.koin.dsl.module
import org.plux.marvelpedia.features.comics.comic_list.data.use_cases.GetComicListUC

val comicsModule = module {
    factory { GetComicListUC() }
}