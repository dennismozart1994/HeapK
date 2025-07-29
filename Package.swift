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
            checksum: "f743ed0c678d46fcfef1bfa47a13506e490fe35ac72289c30ceaa371f35fea22"
        )
        ,
    ]
)