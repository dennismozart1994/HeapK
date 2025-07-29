package com.mozartlabs.heapk.models

/**
 * Heap configuration required for initialization
 * @param projectId Heap project Id to track events e.g: 123456789
 * @param @shouldDisableTextCapture whether to disable Text Interaction Capture or not
 * @param @shouldDisableAccessibilityLabelCapture whether to disable Accessibility Label Interaction Capture or not
 * @param @shouldDisableAdvertiserIdCapture whether to disable Advertiser Id Capture or not
 */
data class HeapKConfig(
    val projectId: String,
    val shouldDisableTextCapture: Boolean,
    val shouldDisableAccessibilityLabelCapture: Boolean,
    val shouldDisableAdvertiserIdCapture: Boolean
)