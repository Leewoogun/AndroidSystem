import com.lwg.setNamespace

plugins {
    alias(libs.plugins.lwg.android.library)
    alias(libs.plugins.lwg.android.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    setNamespace("navigation")
}

dependencies {
    api(libs.androidx.compose.navigation)

    implementation(libs.kotlinx.serialization.json)
}