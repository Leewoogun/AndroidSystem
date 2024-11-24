plugins {
    alias(libs.plugins.lwg.android.application)
}

android {
    namespace = "com.lwg.androidsystem"

    defaultConfig {
        applicationId = "com.lwg.androidsystem"
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
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

}