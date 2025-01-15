import com.lwg.setNamespace

plugins {
    alias(libs.plugins.lwg.android.library)
    alias(libs.plugins.lwg.android.hilt)
    alias(libs.plugins.lwg.android.room)
    id("kotlinx-serialization")
}

android {
    setNamespace("database")

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}

dependencies {
    implementation(libs.junit4)
    implementation(libs.androidx.test.ext)
    implementation(libs.hilt.android.testing)
    implementation(libs.coroutines.test)
    implementation(libs.kotlinx.serialization.json)
    implementation(kotlin("reflect"))

    api(projects.core.model)
}