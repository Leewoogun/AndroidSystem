import com.lwg.configureHiltKotlin
import com.lwg.configureKotest
import com.lwg.configureKotlin
import org.gradle.kotlin.dsl.kotlin

plugins {
    kotlin("jvm")
}

configureKotlin()
configureKotest()
configureHiltKotlin()