This is a Kotlin Multiplatform project targeting Android, iOS.

![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/dennismozart1994/HeapK/ci.yml?color=dark-green)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dennismozart1994_HeapK&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=dennismozart1994_HeapK)
![Sonar Coverage](https://img.shields.io/sonar/coverage/dennismozart1994_HeapK?server=https%3A%2F%2Fsonarcloud.io&color=dark-green)
![GitHub top language](https://img.shields.io/github/languages/top/dennismozart1994/HeapK)
![GitHub License](https://img.shields.io/github/license/dennismozart1994/HeapK?color=dark-grey)

**Heap** does not provide a native KMP library, but they were kind enough to [answer me on how to do it](https://github.com/heap/heap-ios-autocapture-sdk/issues/4). Therefore, this is a KMP library to interact with  Heap.io in a type-safe and consistent way with KMP (Android & iOS). This does not support Web since Heap does not (at least from my knowledge) provide a npm package to install and use with Js.

---

## Features

- Interact with Heap.io to send user behavioral events in a Cross Platform manner
  
---

## Supported Platforms

| Platform                                                                          | Target                                          |
|-----------------------------------------------------------------------------------|-------------------------------------------------|
| <img height="32" width="32" src="https://cdn.simpleicons.org/android/3DDC84" />   | `androidTarget()`                               |
| <img height="32" width="32" src="https://cdn.simpleicons.org/apple/000000" />     | `iosArm64()`, `iosX64()`, `iosSimulatorArm64()` |

---

## Publishing

This library is published to:

- **Android/KMP:** ![GitHub Release](https://img.shields.io/github/v/release/dennismozart1994/HeapK?logo=github&color=brightgreen) [![](https://jitpack.io/v/dennismozart1994/HeapK.svg)](https://jitpack.io/#dennismozart1994/HeapK)
- **iOS:** ![Static Badge](https://img.shields.io/badge/%20SPM-https%3A%2F%2Fgithub.com%2Fdennismozart1994%2FHeapK-blue?logo=apple)

### Installation

#### iOS via Swift Package Manager <img height="16" width="16" src="https://cdn.simpleicons.org/swift/F05138" />

HeapK is published as a Swift Package. Add the following to your `Package.swift` or use Xcode:

1. Then in your project go to File > Add Package Dependency > And search for `https://github.com/dennismozart1994/HeapK`
2. Select the version > Download and install the dependency.

##### Usage example <img height="16" width="16" src="https://cdn.simpleicons.org/swift/F05138" />

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

#### Gradle (Android) <img height="16" width="16" src="https://cdn.simpleicons.org/android/3DDC84" />
Add it in your settings.gradle.kts at the end of repositories:
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        // ONLY add the jitpack url if downloading from Jitpack.io
        maven { url = uri("https://jitpack.io") } 
        // ONLY add the Github packages url if downloading from Github
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/dennismozart1994/HeapK")
            credentials {
                username = <GITHUB_USERNAME> // replace with your github username
                password = <GITHUB_TOKEN> // replace with your Github Personal Access Token
            }
        }
    }
}
```

Add the Heap dependency to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.dennismozart1994.HeapK:shared-android:0.1.1")
}
```

##### Usage example <img height="16" width="16" src="https://cdn.simpleicons.org/android/3DDC84" />

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

#### Gradle (Kotlin Multiplatform) <img height="16" width="16" src="https://cdn.simpleicons.org/kotlin/7F52FF" />

Add it in your settings.gradle.kts at the end of repositories:
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        // ONLY add the jitpack url if downloading from Jitpack.io
        maven { url = uri("https://jitpack.io") } 
        // ONLY add the Github packages url if downloading from Github
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/dennismozart1994/HeapK")
            credentials {
                username = <GITHUB_USERNAME> // replace with your github username
                password = <GITHUB_TOKEN> // replace with your Github Personal Access Token
            }
        }
    }
}
```

Add the Heap dependency to your `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.github.dennismozart1994.HeapK:shared:0.1.1")
            }
        }
    }
}
```

---

## 💻 Build and Test Commands (locally)  <img height="16" width="16" src="https://cdn.simpleicons.org/androidstudio/3DDC84" />

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

## Contributing <img height="16" width="16" src="https://cdn.simpleicons.org/contributorcovenant/5E0D73" />

Contributions are welcome! Please:

- Fork and open a pull request for improvements

---

## License

This project is open source and available under the [MIT License](../LICENSE).
