import com.lwg.setNamespace

plugins {
    alias(libs.plugins.lwg.android.library)
}

android {
    setNamespace("data_api")
}

dependencies {
    implementation(projects.core.model)
}