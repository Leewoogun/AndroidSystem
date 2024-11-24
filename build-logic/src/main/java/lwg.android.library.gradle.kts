import com.lwg.configureCoroutineAndroid
import com.lwg.configureHiltAndroid
import com.lwg.configureKotest
import com.lwg.configureKotlinAndroid

plugins {
    id("com.android.library")
}

configureKotlinAndroid()
configureKotest()
configureCoroutineAndroid()
configureHiltAndroid()