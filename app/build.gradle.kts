plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("dagger.hilt.android.plugin")  // Hilt plugin
    kotlin("kapt")  // Required for annotation processing
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0"
}

android {
    namespace = "com.example.ktordemo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ktordemo"
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

    // Hilt requires kapt to process annotations
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Ktor
    // Ktor core client library for making HTTP requests
    implementation(libs.ktor.client.core)
    // Ktor client for Android platform to support Android-specific features
    implementation(libs.ktor.client.android)
    // Ktor client JSON support for handling JSON data
    implementation(libs.ktor.client.json)
    // Ktor client logging support for logging HTTP requests and responses
    implementation(libs.ktor.client.logging)
    // Ktor client serialization support for serializing and deserializing data
    implementation(libs.ktor.client.serialization)
    // Kotlinx serialization JSON library for JSON serialization and deserialization
    implementation(libs.kotlinx.serialization.json)
    // Ktor serialization with Kotlinx JSON for integrating Ktor with Kotlinx serialization
    implementation(libs.ktor.serialization.kotlinx.json)
    // Ktor client content negotiation support for handling different content types
    implementation(libs.ktor.client.content.negotiation)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Swipe Refresh
    implementation(libs.accompanist.swiperefresh)
}