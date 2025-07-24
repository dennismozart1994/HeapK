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
            url: "https://github.com/dennismozart1994/HeapK/releases/download/0.0.4/HeapK.xcframework.zip",
            checksum: "1a75e315c17cfad4c6df4401f629540a1e6cd0417b7dc6768cf1e2e2f92da742"
        )
        ,
    ]
)