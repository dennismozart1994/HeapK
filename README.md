This is a Kotlin Multiplatform project targeting Android, iOS.

[![](https://jitpack.io/v/dennismozart1994/HeapK.svg)](https://jitpack.io/#dennismozart1994/HeapK)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dennismozart1994_HeapK&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=dennismozart1994_HeapK)

**Heap** does not provide a native KMP library, but they were kind enough to [answer me on how to do it](https://github.com/heap/heap-ios-autocapture-sdk/issues/4). Therefore, this is a KMP library to interact with  Heap.io in a type-safe and consistent way with KMP (Android & iOS). This does not support Web since Heap does not (at least from my knowledge) provide a npm package to install and use with Js.

---

## Features

- Interact with Heap.io to send user behavioral events in a Cross Platform manner
- Kotlin Multiplatform support for:
    - **Android**
    - **iOS**
---

## Platforms

| Platform | Target                                         |
|----------|------------------------------------------------|
| Android  | `androidTarget()`                              |
| iOS      | `iosArm64()`, `iosX64()`, `iosSimulatorArm64()` |

---

## Installation

### iOS via Swift Package Manager

HeapK is published as a Swift Package. Add the following to your `Package.swift` or use Xcode:

1. Then in your project go to File > Add Package Dependency > And search for `https://github.com/dennismozart1994/HeapK`
2. Select the version > Download and install the dependency.

### Gradle (Android)

Add the Heap dependency to your `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.denniverse.heapk:shared-android:<version>")
            }
        }
    }
}
```

### Gradle (Kotlin Multiplatform)

Add the Heap dependency to your `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.denniverse.heapk:shared:<version>")
            }
        }
    }
}
```

> Replace `<library-name>` with the library package name of your new shared library dependent of Heap.
> Replace `<version>` with the latest published version.

### 💻 Build and Test Commands

Getting your project up and running is simple! Here are the commands you need to build and test your
repository:

1. **Build the Project:**

   To build your project, run the following command in the terminal:

   ```bash
   ./gradlew :shared:assemble // Kotlin targets
   ./gradlew :shared:assembleHeapKReleaseXCFramework // iOS targets
   xcodebuild build -scheme HeapK -configuration "Debug" -destination generic/platform=iOS -verbose SKIP_INSTALL=NO BUILD_LIBRARY_FOR_DISTRIBUTION=YES 
   ```

2. **Run Tests:**

   To execute all the tests, use:

   ```bash
   ./gradlew allTests
   ```

   Or if desired, here's how to run tests for each platform.

   Android:

   ```bash
   ./gradlew testDebugUnitTest
   ```

   iOS:

   ```bash
   ./gradlew iosSimulatorArm64Test
   ```

## Usage

### Basic Example

#### iOS

```swift
import HeapK

 // Example, swap with real data
let heapKConfig = HeapKConfig(
    projectId = HEAP_PROJECT_ID, // Your Heap project Id goes here
    shouldDisableTextCapture = true,
    shouldDisableAccessibilityLabelCapture = true,
    shouldDisableAdvertiserIdCapture = true
)
let heapK = HeapKAnalytics()

heapK.initialize(config: heapKConfig)
let nsDictionary: NSDictionary = ["test_message": "Hey I'm a KMP log on iOS"]
heapK.track(action: "test_kmp_ios") // track action without properties

// Convert NSDictionary to Swift Dictionary
if let swiftDictionary = nsDictionary as? [AnyHashable: Any] {
    heapK.track(action: "test_kmp_ios", withProperties: swiftDictionary) // track action with extra properties
} else {
    print("Failed to convert NSDictionary to [AnyHashable: Any]")
}
```

#### Android / Other KMP modules

```kotlin
import com.denniverse.heapk.AppContext
import com.denniverse.heapk.HeapKAnalytics
import com.denniverse.heapk.HeapKConfig

// on Android the Context is REQUIRED to initialize Heap
val heapKContext = AppContext
heapKContext.set(applicationContext)

// Example, swap with real data
val heapKConfig = HeapKConfig(
        projectId = HEAP_PROJECT_ID, // Your Heap project Id goes here
        shouldDisableTextCapture = true,
        shouldDisableAccessibilityLabelCapture = true,
        shouldDisableAdvertiserIdCapture = true
    )

val heapK = HeapKAnalytics()
// Pass the new context to HeapAnalytics BEFORE calling initialize() on Android
heapK.setContext(heapContext)

heapK.initialize(config = heapKConfig)
heapK.track("test_kmp") // track action without properties
heapK.track("test_kmp", "test_message" to "Hey I'm a KMP log on Android") // track action with extra properties
```

---

## Publishing

This library is published to:

- **Android/KMP:** jitpack.io
- **iOS:** GitHub SPM

---

## Contributing

Contributions are welcome! Please:

- Fork and open a pull request for improvements

---
