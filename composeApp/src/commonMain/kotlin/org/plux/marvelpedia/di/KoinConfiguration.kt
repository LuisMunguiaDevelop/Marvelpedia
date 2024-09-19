package org.plux.marvelpedia.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

object KoinConfiguration {

    fun initKoin(config: KoinAppDeclaration? = null){
        startKoin{
            config?.invoke(this)
            modules(viewModelModule, characterListModule)
        }
    }
}