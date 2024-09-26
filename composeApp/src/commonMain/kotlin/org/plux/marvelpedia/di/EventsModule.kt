package org.plux.marvelpedia.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.plux.marvelpedia.features.events.events_list.EventListViewModel
import org.plux.marvelpedia.features.events.events_list.data.GetEventListUC
import org.plux.marvelpedia.features.events.events_search.EventsSearchViewModel

val eventsModule = module {
    viewModel { EventListViewModel(get()) }
    viewModel { EventsSearchViewModel(get()) }
    factory { GetEventListUC() }
}