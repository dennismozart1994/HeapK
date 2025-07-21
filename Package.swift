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
            url: "https://github.com/dennismozart1994/HeapK/releases/download/0.0.1/HeapK.xcframework.zip",
            checksum: "f1e81710d48cd7b4df07029ed6c35d095db324416712ae03e7a9654ef08ab0af"
        )
        ,
    ]
)