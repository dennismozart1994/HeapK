plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.cocoapods) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.heap.android).apply(false)
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kover) apply false
    alias(libs.plugins.kmm.bridge) apply false
    alias(libs.plugins.mokkery) apply false
    alias(libs.plugins.skie) apply false
    alias(libs.plugins.sonarqube) apply true
}