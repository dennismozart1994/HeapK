package com.denniverse.heapk

import com.denniverse.heapk.models.HeapKConfig

expect class HeapKAnalytics {
    internal val heap: HeapK
    fun initialize(config: HeapKConfig)
}