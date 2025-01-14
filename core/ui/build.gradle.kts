import com.lwg.setNamespace

plugins {
    alias(libs.plugins.lwg.android.library)
    alias(libs.plugins.lwg.android.compose)
}

android {
    setNamespace("ui")
}

dependencies {
    api(projects.core.designSystem)
}