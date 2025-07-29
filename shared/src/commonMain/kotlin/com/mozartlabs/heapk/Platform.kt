package com.mozartlabs.heapk

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform