import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.lwg.setNamespace
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.lwg.android.library)
    alias(libs.plugins.lwg.android.hilt)
    id("kotlinx-serialization")
}

// properties 파일 로드
val properties = Properties().apply {
    load(FileInputStream(rootProject.file("local.properties")))
}
fun getApiKey(propertyKey: String): String = gradleLocalProperties(rootDir, providers).getProperty(propertyKey)

android {
    setNamespace("data")

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "tmdb_token",getApiKey("tmdb_token"))
    }
}

dependencies {
    implementation(projects.core.dataApi)
    implementation(projects.core.model)
    implementation(projects.core.util)
    implementation(projects.core.database)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.immutable)
    implementation(libs.kotlinx.datetime)
}