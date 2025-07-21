package com.denniverse.heapk

import kotlin.test.Test
import kotlin.test.assertTrue

class PlatformAndroidTest {
    @Test
    fun `test get Android platform`() {
        assertTrue(getPlatform().name.lowercase().contains("android"))
    }
}