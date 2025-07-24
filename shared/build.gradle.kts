import org.gradle.api.tasks.testing.AbstractTestTask
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.cocoapods)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kmm.bridge)
    alias(libs.plugins.kover)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.skie)
    alias(libs.plugins.mokkery)
}

group = libs.versions.library.group.get()
version = libs.versions.library.version.get()

android {
    namespace = libs.versions.library.group.get()
    compileSdk = 36

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    defaultConfig {
        minSdk = 29
    }

    // https://youtrack.jetbrains.com/issue/KT-43883/Lint-failure-ClassCastExceptionNonNullableMutableLiveDataDetector
    lint {
        disable.add("NullSafeMutableLiveData")
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }

    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

configurations {
    create("android_aar")
}

val aarFile = layout.buildDirectory.file("outputs/aar/shared-release.aar")
val aarArtifact = artifacts.add("android_aar", aarFile.get().asFile) {
    type = "aar"
    builtBy("assembleRelease")
}

kmmbridge {
    spm()
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    targets.configureEach {
        compilations.configureEach {
            compileTaskProvider.get().compilerOptions {
                // https://youtrack.jetbrains.com/issue/KT-61573
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }

    cocoapods {
        name = "HeapK"
        summary = "Heap.io dependency for Kotlin Multiplatform"
        homepage = "Link to the Shared Module homepage"
        version = libs.versions.library.version.get()
        ios.deploymentTarget = "16.0"

        // https://github.com/heap/heap-ios-sdk
        pod("Heap") {
            version = "9.1.0"
        }

        framework {
            // substitute to your lib name
            // Changing the name will require changing configs in iosApp in Xcode to compile the sample app also
            // This is basically the name of the import inside the xcode project when importing the library
            // Also inside the .xcodeproj configs there are mentions to "shared" under frameworks
            // That will need to be replaced to whatever you change here to in order to compile properly the sample app
            baseName = "HeapK"
            binaryOption("bundleId", libs.versions.library.group.get())

            //Any dependency you add for ios should be added here using export()
            export(libs.kotlin.stdlib)
            isStatic = true
        }

        // Maps custom Xcode configuration to NativeBuildType
        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = NativeBuildType.RELEASE
    }
    
    sourceSets {
        commonMain.dependencies {
            api(libs.kotlin.stdlib)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            // https://skie.touchlab.co/configuration/
            implementation(libs.skie.configuration.annotations)
            implementation(libs.kermit)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlin.test.annotations.common)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.kermit.test)
        }
        androidMain.dependencies {
            implementation(libs.heap.android.core)
            implementation(libs.heap.autocapture.view)
        }
        androidUnitTest.dependencies {
            implementation(libs.androidx.test.core)
            implementation(libs.kotlin.test.junit)
            implementation(libs.junit)
            implementation(libs.robolectric)
        }
    }
}


publishing {
    publications { }
    publications.withType<MavenPublication>().getByName("kotlinMultiplatform") {
        groupId = libs.versions.library.group.get()
        artifactId = "shared"
        version = libs.versions.library.version.get()
        artifact(aarArtifact)
    }
}

skie {
    features {
        // https://skie.touchlab.co/features/flows-in-swiftui
        enableSwiftUIObservingPreview = true
    }
    build {
        // https://skie.touchlab.co/configuration/swift-compiler
        produceDistributableFramework()
    }
    analytics {
        // https://skie.touchlab.co/Analytics
        enabled.set(false)
    }
}

// A separated ticket will be opened to check sonarqube support in KMP
// For more info, refers to guides on sonar documentation for Sonar Gradle plugin at
// https://community.sonarsource.com/t/how-to-integrate-sonarqube-with-kotlinmultiplatform-project/107531/2
// https://community.sonarsource.com/t/kotlin-multiplatform-support-is-here/92577
// https://docs.sonarsource.com/sonarqube-server/latest/analyzing-source-code/scanners/sonarscanner-for-gradle/
sonar {
    properties {
        if (System.getenv("CI_COMMIT_REF_NAME") != "main") {
            property("sonar.newCode.referenceBranch", "main")
        }
    }
}

tasks.register("koverAggregateReport") {
    dependsOn(":shared:check", "koverHtmlReport")
    finalizedBy("koverXmlReport")
}

tasks.withType<org.jetbrains.kotlin.gradle.targets.native.tasks.PodGenTask>().configureEach {
    doLast {
        podfile.get().apply { writeText(readText().replace("config.build_settings['CODE_SIGNING_ALLOWED'] = \"NO\"", "config.build_settings['CODE_SIGNING_ALLOWED'] = \"NO\"\nconfig.build_settings['SWIFT_ACTIVE_COMPILATION_CONDITIONS'] = '\$(inherited) LD_OBJC_EXCLUDE_PURE_SWIFT_APIS'\n")) }
    }
}

// Adds test debug info into the CICD console log
tasks.withType<Test> {
    testLogging {
        events("FAILED", "SKIPPED", "STANDARD_OUT", "STANDARD_ERROR")
        exceptionFormat = TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}

tasks.withType<Test> {
    outputs.upToDateWhen { false }
}

tasks.withType<AbstractTestTask> {
    outputs.upToDateWhen { false }
}
