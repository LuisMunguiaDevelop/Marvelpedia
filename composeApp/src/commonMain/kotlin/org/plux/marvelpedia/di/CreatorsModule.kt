package org.plux.marvelpedia.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.plux.marvelpedia.features.creators.creators_list.CreatorsListViewModel
import org.plux.marvelpedia.features.creators.creators_list.data.GetCreatorsListUC
import org.plux.marvelpedia.features.creators.creators_search.CreatorsSearchViewModel

val creatorsModule = module {
    viewModel { CreatorsListViewModel(get()) }
    viewModel { CreatorsSearchViewModel(get()) }
    factory { GetCreatorsListUC() }
}