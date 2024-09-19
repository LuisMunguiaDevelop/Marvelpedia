package org.plux.marvelpedia.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.plux.marvelpedia.features.hero_list.HeroListViewModel

val viewModelModule = module {
    viewModel { HeroListViewModel( get() ) }
}