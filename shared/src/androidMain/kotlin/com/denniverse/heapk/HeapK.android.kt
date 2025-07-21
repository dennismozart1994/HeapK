package com.denniverse.heapk

import android.os.Build
import com.denniverse.heapk.models.AppContext
import com.denniverse.heapk.models.HeapKConfig
import io.heap.autocapture.ViewAutocaptureSDK
import io.heap.core.Heap
import io.heap.core.Options

internal actual fun startHeap(
    context: AppContext,
    config: HeapKConfig
) {
    require(context.get() != null) {
        "Context must be provided through AppContext.set(context: Context) before initializing Heap on Android!"
    }

    context.get()?.let {
        Heap.startRecording(
            context = it,
            envId = config.projectId,
            Options(
                disableInteractionTextCapture = config.shouldDisableTextCapture,
                disableInteractionAccessibilityLabelCapture = config.shouldDisableAccessibilityLabelCapture,
                captureAdvertiserId = !config.shouldDisableAdvertiserIdCapture
            )
        )
        val eventProps = mapOf<String, String>(
            "platform" to getPlatform().name,
            "manufacturer" to Build.MANUFACTURER,
            "model" to Build.MODEL,
            "sdk" to Build.VERSION.SDK_INT.toString(),
            "hardware" to Build.HARDWARE,
            "device" to Build.DEVICE
        )
        Heap.addEventProperties(eventProps)
        ViewAutocaptureSDK.register()
    }
}

internal actual fun addHeapUserProperties(
    userProperties: Map<String, String?>
) {
    Heap.addUserProperties(userProperties.removeNullValuePairs())
}

internal actual fun trackHeap(
    action: String,
    vararg data: Pair<String, String>
) {
    Heap.track(action, mapOf(*data))
}