package org.plux.marvelpedia

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Marvelpedia",
    ) {
        App()
    }
}