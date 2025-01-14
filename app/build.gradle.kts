plugins {
    alias(libs.plugins.lwg.android.application)
}

android {
    namespace = "com.lwg"

    defaultConfig {
        applicationId = "com.lwg"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.dataApi)
    implementation(projects.core.model)

    implementation(projects.feature.main)
}