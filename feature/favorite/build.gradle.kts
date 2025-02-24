import com.lwg.setNamespace

plugins {
    alias(libs.plugins.lwg.android.feature)
}

android {
    setNamespace("favorite")
}

dependencies {
    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    implementation(libs.androidx.biometric)

    implementation(projects.core.util)
    implementation(projects.core.dataApi)
    implementation(projects.core.model)
}