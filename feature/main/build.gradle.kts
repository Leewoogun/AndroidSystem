import com.lwg.setNamespace

plugins {
    alias(libs.plugins.lwg.android.feature)
}

android {
    setNamespace("main")
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.designSystem)
    implementation(projects.core.navigation)

    implementation(projects.feature.home)
    implementation(projects.feature.favorite)
    implementation(projects.feature.calendar)
}