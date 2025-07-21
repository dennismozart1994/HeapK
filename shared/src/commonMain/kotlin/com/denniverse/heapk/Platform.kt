package com.denniverse.heapk

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform