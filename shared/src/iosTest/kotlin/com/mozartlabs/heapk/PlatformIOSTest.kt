package com.mozartlabs.heapk

import kotlin.test.Test
import kotlin.test.assertTrue

class PlatformIOSTest {
    @Test
    fun `test get iOS platform`() {
        assertTrue(getPlatform().name.lowercase().contains("ios"))
    }
}