package com.denniverse.heapk

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HeapKTest {
    @Test
    fun `test removeNullValuePairs removes pairs with null values`() {
        val nullValuePair = Pair("uno", null)
        val map: Map<String, String?> = mapOf(nullValuePair)

        assertTrue(map.removeNullValuePairs().isEmpty())
    }

    @Test
    fun `test removeNullValuePairs does not remove pairs with non-null values`() {
        val nullValuePair = Pair("uno", null)
        val nonNullValuePair = Pair("dos", "notNull")

        val map: Map<String, String?> = mapOf(nullValuePair, nonNullValuePair)
        val mapWithoutNulls = map.removeNullValuePairs()

        assertFalse(mapWithoutNulls.contains(nullValuePair.first))
        assertTrue(mapWithoutNulls.contains(nonNullValuePair.first))
    }
}