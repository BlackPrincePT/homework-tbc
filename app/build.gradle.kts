plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.google.devtools.ksp)

    id("kotlin-kapt")

    // Navigation
    alias(libs.plugins.navigation.safeargs.kotlin)

    // DI
    alias(libs.plugins.dagger.hilt.android)

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
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "BASE_URL", "\"https://reqres.in/\"")
            buildConfigField("String", "LOGIN_ENDPOINT", "\"api/login\"")
            buildConfigField("String", "REGISTER_ENDPOINT", "\"api/register\"")
            buildConfigField("String", "USERS_ENDPOINT", "\"api/users\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://reqres.in/\"")
            buildConfigField("String", "LOGIN_ENDPOINT", "\"api/login\"")
            buildConfigField("String", "REGISTER_ENDPOINT", "\"api/register\"")
            buildConfigField("String", "USERS_ENDPOINT", "\"api/users\"")
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

    // UI
    implementation (libs.glide)
    implementation(libs.androidx.paging.runtime)

    // Network
    implementation(libs.retrofit)
    implementation(libs.converter.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Cache
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.paging)
}