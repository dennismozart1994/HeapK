package com.denniverse.heapk

import cocoapods.Heap.Heap
import cocoapods.Heap.HeapOptions
import com.denniverse.heapk.models.AppContext
import com.denniverse.heapk.models.HeapKConfig
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
internal actual fun startHeap(
    context: AppContext,
    config: HeapKConfig
) {
    val options = HeapOptions()
    if (config.shouldDisableTextCapture) options.disableTextCapture()
    if (config.shouldDisableAccessibilityLabelCapture) options.disableAccessibilityLabelCapture()
    if (config.shouldDisableAdvertiserIdCapture) options.disableAdvertiserIdCapture()

    Heap.initialize(
        envId = config.projectId,
        withOptions = options
    )
    Heap.addEventProperties(mapOf("platform" to getPlatform().name))
}

@OptIn(ExperimentalForeignApi::class)
internal actual fun addHeapUserProperties(
    userProperties: Map<String, String?>
) {
    Heap.addUserProperties(userProperties.removeNullValuePairs().mapKeys { it.key }.mapValues { it.value })
}

@OptIn(ExperimentalForeignApi::class)
internal actual fun trackHeap(
    action: String,
    vararg data: Pair<String, String>
) {
    Heap.track(action, mapOf(*data))
}