package org.plux.marvelpedia.di

import org.koin.dsl.module
import org.plux.marvelpedia.features.hero_list.data.use_cases.get_hero_list.GetHeroListUC

val heroListModule = module {
    factory { GetHeroListUC() }
}