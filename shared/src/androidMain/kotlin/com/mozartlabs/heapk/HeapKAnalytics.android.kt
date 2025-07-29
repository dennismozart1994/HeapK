package com.mozartlabs.heapk

import com.mozartlabs.heapk.models.AppContext
import com.mozartlabs.heapk.models.HeapKConfig

actual class HeapKAnalytics {
    internal actual val heap: HeapK = HeapK()

    fun setContext(context: AppContext) = heap.setContext(context)

    actual fun initialize(config: HeapKConfig) = heap.initialize(config)

    fun track(action: String, vararg data:Pair<String, String>) = heap.track(action, *data)
}