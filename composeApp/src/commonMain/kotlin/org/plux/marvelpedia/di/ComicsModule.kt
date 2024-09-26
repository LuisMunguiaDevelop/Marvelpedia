package org.plux.marvelpedia.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.plux.marvelpedia.features.comics.comic_list.data.use_cases.GetComicListUC
import org.plux.marvelpedia.features.comics.comic_search.ComicSearchViewModel

val comicsModule = module {
    viewModel { ComicSearchViewModel(get()) }
    factory { GetComicListUC() }
}