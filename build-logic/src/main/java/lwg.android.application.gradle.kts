import com.lwg.configureHiltAndroid
import com.lwg.configureKotestAndroid
import com.lwg.configureKotlinAndroid


plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()
configureKotestAndroid()
