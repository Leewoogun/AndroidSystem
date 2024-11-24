import com.lwg.configureHiltAndroid
import com.lwg.libs

plugins {
    id("lwg.android.library")
    id("lwg.android.compose")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

configureHiltAndroid()

dependencies {
    val libs = project.extensions.libs
    implementation(libs.findLibrary("hilt.navigation.compose").get())
    implementation(libs.findLibrary("androidx.compose.material.icon").get())
    androidTestImplementation(libs.findLibrary("androidx.compose.navigation.test").get())
    androidTestImplementation(libs.findLibrary("androidx.compose.ui.test").get())

    implementation(libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
    implementation(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
    implementation(libs.findLibrary("kotlinx.immutable").get())
}