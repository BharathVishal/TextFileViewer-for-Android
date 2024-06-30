/*
 *
 * Copyright 2023-2024 Bharath Vishal G
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("maven-publish")
}

android {
    namespace = "com.bharathvishal.textfileviewer"
    compileSdk = 34
    defaultConfig {
        vectorDrawables {
            useSupportLibrary = true
        }
        minSdk = 22
        testOptions.targetSdk = 34
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    packaging {
        jniLibs {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.jetbrains.kotlinx.couroutine)
    implementation(libs.coil.kt)
    implementation(libs.jetbrains.kotlin.stdlib)
    implementation(libs.google.android.material)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.windowsize)
    implementation(libs.androidx.compose.material3.windowsize)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.core.splashscreen)
    implementation(libs.androidx.compose.material.material.icons)
    implementation(libs.google.accompanist.accompanist)
    implementation(libs.androidx.viewpager2.viewpager2)
    implementation(libs.firebase.analytics)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.composeuigraphics)
    implementation(libs.androidx.core.ktx)
    implementation(platform (libs.androidx.compose.bom))
    androidTestImplementation(platform (libs.androidx.compose.bom))
}

afterEvaluate {
    publishing {
        publications {
            register("release", MavenPublication::class) {

                // Applies the component for the release build variant.
                // NOTE : Delete this line code if you publish Native Java / Kotlin Library
                from(components["release"])

                // Library Package Name (Example : "com.frogobox.androidfirstlib")
                groupId = "com.github.BharathVishal"

                // Library Name / Module Name (Example : "androidfirstlib")
                artifactId = "TextFileViewerAndroid"

                // Version Library Name
                version = "1.3.3"
            }
        }
    }
}
