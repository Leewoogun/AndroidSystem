import com.lwg.setNamespace

plugins {
    alias(libs.plugins.lwg.android.feature)
}

android {
    setNamespace("home")
}

dependencies {
    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}