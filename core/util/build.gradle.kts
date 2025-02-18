import com.lwg.setNamespace

plugins {
    alias(libs.plugins.lwg.android.library)
}

android {
    setNamespace("utils")

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.androidx.biometric)
}