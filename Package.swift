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
            url: "https://github.com/dennismozart1994/HeapK/releases/download/0.1.0/HeapK.xcframework.zip",
            checksum: "ce646ed1a063a6974e19f67d6d8c3cea420be15e3ab0d93bed4116bc3c8ed7fe"
        )
        ,
    ]
)