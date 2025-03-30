import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // Compose
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.kapt)

    alias(libs.plugins.kotlin.serialization)

    // Navigation
    alias(libs.plugins.navigation.safeargs.kotlin)

    // DI
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.perullheim.homework"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.perullheim.homework"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                gradleLocalProperties(rootDir, providers).getProperty("BASE_URL")
            )

            buildConfigField(
                type = "String",
                name = "ACCOUNTS_ENDPOINT",
                gradleLocalProperties(rootDir, providers).getProperty("ACCOUNTS_ENDPOINT")
            )

            buildConfigField(
                type = "String",
                name = "CHECK_ACCOUNT_ENDPOINT",
                gradleLocalProperties(rootDir, providers).getProperty("CHECK_ACCOUNT_ENDPOINT")
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Compose
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.kotlinx.serialization.json)

    // Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Network
    implementation(libs.retrofit)
    implementation(libs.converter.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
}