import com.lwg.setNamespace

plugins {
    alias(libs.plugins.lwg.android.library)
    alias(libs.plugins.lwg.android.compose)
}

android {
    setNamespace("designsystem")

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.landscapist.coil)
    implementation(libs.landscapist.animation)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    implementation(libs.androidx.activity.compose)
    implementation(libs.ballon)

    implementation(projects.core.util)
}