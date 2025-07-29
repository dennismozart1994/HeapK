@ -1,25 +0,0 @@
// swift-tools-version:5.3
import PackageDescription

let packageName = "HeapK"

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            url: "https://github.com/dennismozart1994/HeapK/releases/download/0.0.6/HeapK.xcframework.zip",
            checksum: "aa5f88055f7934bed4ee46a7b9a57cb14adbd61865b4c626da7488a5c7b59e7b"
        )
        ,
    ]
)