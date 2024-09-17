package org.plux.marvelpedia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform