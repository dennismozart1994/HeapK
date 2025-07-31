package com.mozartlabs.heapk

import com.mozartlabs.heapk.models.HeapKConfig

expect class HeapKAnalytics() {
    internal val heap: HeapK
    fun initialize(config: HeapKConfig)
}