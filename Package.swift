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
            url: "https://github.com/dennismozart1994/HeapK/releases/download/0.1.1/HeapK.xcframework.zip",
            checksum: "e5d59107eb801d0039c703a39e2a5e0a34a44cd70e49d792f8decf6b30ac1eb7"
        )
        ,
    ]
)