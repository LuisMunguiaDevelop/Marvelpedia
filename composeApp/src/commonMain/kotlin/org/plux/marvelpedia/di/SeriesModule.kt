package org.plux.marvelpedia.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.plux.marvelpedia.features.series.series_list.SeriesListViewModel
import org.plux.marvelpedia.features.series.series_list.data.GetSeriesListUC
import org.plux.marvelpedia.features.series.series_search.SeriesSearchViewModel

val seriesModule = module {
    viewModel { SeriesListViewModel(get()) }
    viewModel { SeriesSearchViewModel(get()) }
    factory { GetSeriesListUC() }
}