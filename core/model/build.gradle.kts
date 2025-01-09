plugins {
    alias(libs.plugins.lwg.kotlin.library)
    id("kotlinx-serialization")
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}