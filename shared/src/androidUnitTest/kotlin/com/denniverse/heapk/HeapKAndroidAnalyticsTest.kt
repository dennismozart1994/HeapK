package com.denniverse.heapk

import androidx.test.core.app.ApplicationProvider
import com.denniverse.heapk.models.AppContext
import com.denniverse.heapk.models.HeapKConfig
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.test.BeforeTest
import kotlin.test.Test

@RunWith(RobolectricTestRunner::class)
class HeapKAndroidAnalyticsTest {
    private val config = HeapKConfig(
        projectId = "123456789",
        shouldDisableTextCapture = true,
        shouldDisableAccessibilityLabelCapture = true,
        shouldDisableAdvertiserIdCapture = true
    )
    private val analytics = HeapKAnalytics()
    private val context = AppContext

    @BeforeTest
    fun setUp() {
        context.set(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun `Set context call Heap utils implementation`() {
        analytics.setContext(context)
        verify(VerifyMode.exactly(1)) { analytics.heap.setContext(context = context) }
    }

    @Test
    fun `Initialize call Heap Utils implementation`() {
        analytics.setContext(context)
        analytics.initialize(config = config)
        verify(VerifyMode.exactly(1)) { analytics.heap.initialize(config = config) }
    }

    @Test
    fun `Track call Heap Utils implementation`() {
        val params = "test_message" to "message"
        analytics.initialize(config = config)
        analytics.track("test")
        analytics.track("testWithParams", params)
        verify(VerifyMode.exactly(1)) { analytics.heap.track("test") }
        verify(VerifyMode.exactly(1)) { analytics.heap.track("testWithParams", params) }
    }
}