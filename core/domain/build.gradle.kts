import com.lwg.setNamespace

plugins {
    alias(libs.plugins.lwg.android.library)
}

android {
    setNamespace("domain")
}

dependencies {
    implementation(projects.core.dataApi)
    implementation(projects.core.model)
    implementation(projects.core.util)
    implementation(projects.core.network)
}