plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "lwg.android.hilt"
            implementationClass = "com.lwg.HiltAndroidPlugin"
        }
        register("kotlinHilt") {
            id = "lwg.kotlin.hilt"
            implementationClass = "com.lwg.HiltKotlinPlugin"
        }
        register("androidRoom") {
            id = "lwg.android.room"
            implementationClass = "com.lwg.AndroidRoomPlugin"
        }
    }
}