buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.android.tools.build.gradle.plugin)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.hilt.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.sample.android.application) apply false
    alias(libs.plugins.sample.android.library) apply false
    alias(libs.plugins.sample.android.test) apply false
    alias(libs.plugins.sample.compose) apply false
}