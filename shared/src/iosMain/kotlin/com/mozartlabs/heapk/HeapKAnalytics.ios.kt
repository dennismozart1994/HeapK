package com.mozartlabs.heapk

import com.mozartlabs.heapk.models.HeapKConfig
import platform.Foundation.NSDictionary
import platform.Foundation.NSString
import platform.Foundation.allKeys

actual class HeapKAnalytics {
    internal actual val heap: HeapK = HeapK()

    actual fun initialize(config: HeapKConfig) = heap.initialize(config)

    fun track(action: String) = heap.track(action)

    fun track(action: String, withProperties: NSDictionary) = heap.track(action, *withProperties.toVarargPairs())
}

fun NSDictionary.toVarargPairs(): Array<out Pair<String, String>> {
    val keys = this.allKeys().map { it as NSString }
    val pairs = mutableListOf<Pair<String, String>>()
    for (key in keys) {
        (this.objectForKey(key) as? NSString)?.let { value ->
            pairs.add(Pair(key.toString(), value.toString()))
        }
    }
    return pairs.toTypedArray()
}