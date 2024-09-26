package org.plux.marvelpedia.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.plux.marvelpedia.features.series.series_list.SeriesListViewModel
import org.plux.marvelpedia.features.series.series_list.data.GetSeriesListUC

val seriesModule = module {
    viewModel { SeriesListViewModel(get()) }
    factory { GetSeriesListUC() }
}