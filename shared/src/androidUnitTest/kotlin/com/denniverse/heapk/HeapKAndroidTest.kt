package com.denniverse.heapk

import androidx.test.core.app.ApplicationProvider
import com.denniverse.heapk.models.AppContext
import com.denniverse.heapk.models.HeapKConfig
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class HeapKAndroidTest {
    private val config = HeapKConfig(
        projectId = "123456789",
        shouldDisableTextCapture = true,
        shouldDisableAccessibilityLabelCapture = true,
        shouldDisableAdvertiserIdCapture = true
    )

    @Test
    fun `Test Heap can be initialized` () {
        val heap = HeapK()
        assertFalse(heap.isInit)
        val context = AppContext
        context.set(ApplicationProvider.getApplicationContext())
        heap.setContext(context)
        heap.initialize(config)
        assertTrue(heap.isInit)
        assertNotNull(heap.context.get())
    }
}

class HeapKAndroidExceptionTest {
    @Test(expected = IllegalArgumentException::class)
    fun `Test Heap initialization exception if context not provided on Android`() {
        val config = HeapKConfig(
            projectId = "123456789",
            shouldDisableTextCapture = true,
            shouldDisableAccessibilityLabelCapture = true,
            shouldDisableAdvertiserIdCapture = true
        )

        val heap = HeapK()
        assertFalse(heap.isInit)
        heap.initialize(config)
        assertTrue(heap.isInit)
    }
}