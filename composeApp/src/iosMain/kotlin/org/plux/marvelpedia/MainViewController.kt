package org.plux.marvelpedia

import androidx.compose.ui.window.ComposeUIViewController
import org.plux.marvelpedia.di.KoinConfiguration

fun MainViewController() = ComposeUIViewController(
    configure = { KoinConfiguration.initKoin() }
) {
    App()

}