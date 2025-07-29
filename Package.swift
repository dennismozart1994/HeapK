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
            url: "https://github.com/dennismozart1994/HeapK/releases/download/0.0.7/HeapK.xcframework.zip",
            checksum: "be2a8b999604870ca14bd88e782bf0f497615579060171b5350bd3b0bfb60329"
        )
        ,
    ]
)