package com.mozartlabs.heapk

import com.mozartlabs.heapk.models.HeapKConfig
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HeapKIOSTest {
    @Test
    fun `Test Heap can be initialized` () {
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