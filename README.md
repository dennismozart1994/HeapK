This is a Kotlin Multiplatform project targeting Android, iOS.

![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/dennismozart1994/HeapK/ci.yml?color=dark-green)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dennismozart1994_HeapK&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=dennismozart1994_HeapK)
![Sonar Coverage](https://img.shields.io/sonar/coverage/dennismozart1994_HeapK?server=https%3A%2F%2Fsonarcloud.io&color=dark-green)
![GitHub top language](https://img.shields.io/github/languages/top/dennismozart1994/HeapK)
![GitHub License](https://img.shields.io/github/license/dennismozart1994/HeapK?color=dark-grey)


Heap Analytics does not provide a native KMP library, but they were kind enough to [answer me on how to do it](https://github.com/heap/heap-ios-autocapture-sdk/issues/4). Therefore, this is a KMP library to interact with Heap.io in a type-safe and consistent way with KMP (Android & iOS). This does not support Web since Heap does not (at least from my knowledge) provide a npm package to install and use with Js.

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

## Contributing

Contributions are welcome! Please:

- Fork and open a pull request for improvements

---

## License

This project is open source and available under the [MIT License](LICENSE).
