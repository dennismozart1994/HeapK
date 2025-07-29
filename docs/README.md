This is a Kotlin Multiplatform project targeting Android, iOS.

[![](https://jitpack.io/v/dennismozart1994/HeapK.svg)](https://jitpack.io/#dennismozart1994/HeapK) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dennismozart1994_HeapK&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=dennismozart1994_HeapK)

**Heap** does not provide a native KMP library, but they were kind enough to [answer me on how to do it](https://github.com/heap/heap-ios-autocapture-sdk/issues/4). Therefore, this is a KMP library to interact with  Heap.io in a type-safe and consistent way with KMP (Android & iOS). This does not support Web since Heap does not (at least from my knowledge) provide a npm package to install and use with Js.

---

## Features

- Interact with Heap.io to send user behavioral events in a Cross Platform manner
- Kotlin Multiplatform support for:
    - **Android**
    - **iOS**
---

## Platforms

| Platform | Target                                          |
|----------|-------------------------------------------------|
| Android  | `androidTarget()`                               |
| iOS      | `iosArm64()`, `iosX64()`, `iosSimulatorArm64()` |

---

## Publishing

This library is published to:

- **Android:** [![](https://jitpack.io/v/dennismozart1994/HeapK.svg)](https://jitpack.io/#dennismozart1994/HeapK)
- **iOS:** GitHub SPM (package url: `https://github.com/dennismozart1994/HeapK`)

### Installation

#### iOS via Swift Package Manager

HeapK is published as a Swift Package. Add the following to your `Package.swift` or use Xcode:

1. Then in your project go to File > Add Package Dependency > And search for `https://github.com/dennismozart1994/HeapK`
2. Select the version > Download and install the dependency.

##### Usage example

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

#### Gradle (Android)
Add it in your settings.gradle.kts at the end of repositories:
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add the Heap dependency to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.dennismozart1994.HeapK:shared-android:0.0.4")
}
```

##### Usage example

```kotlin
import com.mozartlabs.heapk.AppContext
import com.mozartlabs.heapk.HeapKAnalytics
import com.mozartlabs.heapk.HeapKConfig

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
// Provide the Android context to HeapAnalytics BEFORE calling initialize() on Android
heapK.setContext(heapContext)

heapK.initialize(config = heapKConfig)
heapK.track("test_kmp") // track action without properties
heapK.track("test_kmp", "test_message" to "Hey I'm a KMP log on Android") // track action with extra properties
```

#### Gradle (Kotlin Multiplatform)

Add it in your settings.gradle.kts at the end of repositories:
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add the Heap dependency to your `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.github.dennismozart1994.HeapK:shared:0.0.4")
            }
        }
    }
}
```

---

## 💻 Build and Test Commands (locally)

Getting HeapK project locally up and running is simple! After a successful repository clone/fork, here are some commands you can use to build and test your
repository:

1. **Build the Project:**

   To build your project, run the following command in the terminal:

   ```bash
   ./gradlew :shared:assemble // Android target
   ./gradlew :shared:assembleHeapKReleaseXCFramework // iOS target
   xcodebuild build -scheme HeapK -configuration "Debug" -destination generic/platform=iOS -verbose SKIP_INSTALL=NO BUILD_LIBRARY_FOR_DISTRIBUTION=YES 
   ```

2. **Run Tests:**

   To execute all the tests, use:

   ```bash
   ./gradlew check
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

---

## Contributing

Contributions are welcome! Please:

- Fork and open a pull request for improvements

---

## License

This project is open source and available under the [MIT License](../LICENSE).
