package org.plux.marvelpedia.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.plux.marvelpedia.features.creators.creators_list.CreatorsListViewModel
import org.plux.marvelpedia.features.creators.creators_list.data.GetCreatorsListUC

val creatorsModule = module {
    viewModel { CreatorsListViewModel(get()) }
    factory { GetCreatorsListUC() }
}