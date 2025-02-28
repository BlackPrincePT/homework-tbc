plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)

    id("kotlin-kapt")

    // Navigation
    alias(libs.plugins.navigation.safeargs.kotlin)

    // DI
    alias(libs.plugins.dagger.hilt.android)

    // Map
    alias(libs.plugins.secrets.gradle)

    // Network
    alias(libs.plugins.kotlin.serialization)
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

        buildConfigField("String", "BASE_URL", "\"https://run.mocky.io/v3/\"")
        buildConfigField("String", "ADDRESS_ENDPOINT", "\"c4c64996-4ed9-4cbc-8986-43c4990d495a\"")
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Network
    implementation(libs.retrofit)
    implementation(libs.converter.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Map
    implementation(libs.secrets.gradle.plugin)
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
}