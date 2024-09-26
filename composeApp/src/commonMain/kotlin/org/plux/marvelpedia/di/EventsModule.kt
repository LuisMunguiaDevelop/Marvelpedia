package org.plux.marvelpedia.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.plux.marvelpedia.features.events.events_list.EventListViewModel
import org.plux.marvelpedia.features.events.events_list.data.GetEventListUC

val eventsModule = module {
    viewModel { EventListViewModel(get()) }
    factory { GetEventListUC() }
}