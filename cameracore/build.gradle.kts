plugins {
    id ("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(Versions.TARGET_SDK_VERSION)
    buildToolsVersion(Versions.BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(Versions.MIN_SDK_VERSION)
        targetSdkVersion(Versions.TARGET_SDK_VERSION)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }

    dataBinding.isEnabled = true

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.appcompat:appcompat:${Versions.ANDROIDX_APPCOMPAT_VERSION}")
    implementation("androidx.core:core-ktx:${Versions.ANDROIDX_CORE_KTS_VERSION}")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLINX_COROUTINES_VERSION}")

    api("androidx.camera:camera-camera2:${Versions.CAMERAX_VERSION}")
    api("androidx.camera:camera-lifecycle:${Versions.CAMERAX_VERSION}")
    api("androidx.camera:camera-view:${Versions.CAMERA_VIEW_VERSION}")

    //Hilt
    implementation("com.google.dagger:hilt-android:${Versions.HILT_VERSION}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.HILT_VERSION}")
}