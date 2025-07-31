package com.mozartlabs.heapk

import com.mozartlabs.heapk.models.HeapKConfig
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSMutableDictionary
import platform.Foundation.NSString
import platform.Foundation.create
import kotlin.test.Test

@OptIn(BetaInteropApi::class, ExperimentalForeignApi::class)
class HeapKIOSAnalyticsTest {
    private val config = HeapKConfig(
        projectId = "123456789",
        shouldDisableTextCapture = true,
        shouldDisableAccessibilityLabelCapture = true,
        shouldDisableAdvertiserIdCapture = true
    )
    private val analytics = HeapKAnalytics()

    @Test
    fun `Initialize call Heap Utils implementation`() {
        analytics.initialize(config = config)
        verify(VerifyMode.exactly(1)) { analytics.heap.initialize(config = config) }
    }

    @Test
    fun `Track call Heap Utils implementation`() {
        analytics.initialize(config = config)
        analytics.track("test")
        verify(VerifyMode.exactly(1)) { analytics.heap.track("test") }
    }

    @Test
    fun `Track with options call Heap Utils implementation`() {
        val withProperties = NSMutableDictionary()

        withProperties.setObject(
            NSString.create("value", null),
            forKey = NSString.create("key", null),
        )
        analytics.initialize(config = config)
        analytics.track("test", withProperties)
        verify { analytics.heap.track("test", *withProperties.toVarargPairs()) }
    }

    @Test
    fun `Track does only calls Heap after initialization`() {
        analytics.track("test")
        verify(VerifyMode.exactly(0)) { analytics.heap.track("test") }

        analytics.initialize(config = config)
        analytics.track("test")
        verify(VerifyMode.exactly(1)) { analytics.heap.track("test") }
    }
}