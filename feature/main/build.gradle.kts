import com.lwg.setNamespace

plugins {
    alias(libs.plugins.lwg.android.feature)
}

android {
    setNamespace("main")
}

dependencies {
    implementation(projects.core.network)
}