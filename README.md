This is a Kotlin Multiplatform project targeting Android, iOS.

[![](https://jitpack.io/v/dennismozart1994/HeapK.svg)](https://jitpack.io/#dennismozart1994/HeapK) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dennismozart1994_HeapK&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=dennismozart1994_HeapK)

Heap Analytics does not provide a native KMP library, but they were kind enough to [answer me on how to do it](https://github.com/heap/heap-ios-autocapture-sdk/issues/4). Therefore, this is a KMP library to interact with Heap.io in a type-safe and consistent way with KMP (Android & iOS). This does not support Web since Heap does not (at least from my knowledge) provide a npm package to install and use with Js.

---

## Supported Platforms

| Platform | Target                                          |
|----------|-------------------------------------------------|
| Android  | `androidTarget()`                               |
| iOS      | `iosArm64()`, `iosX64()`, `iosSimulatorArm64()` |

---

## Publishing

This library is published to:

- **Android/KMP:** [![](https://jitpack.io/v/dennismozart1994/HeapK.svg)](https://jitpack.io/#dennismozart1994/HeapK)
- **iOS:** GitHub SPM (package url: `https://github.com/dennismozart1994/HeapK`)

## Contributing

Contributions are welcome! Please:

- Fork and open a pull request for improvements

---
