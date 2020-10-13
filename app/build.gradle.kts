plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.TARGET_SDK_VERSION)
    buildToolsVersion(Versions.BUILD_TOOLS_VERSION)

    defaultConfig {
        applicationId = "lgcode.me.travelnotes"
        minSdkVersion(Versions.MIN_SDK_VERSION)
        targetSdkVersion(Versions.TARGET_SDK_VERSION)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    dataBinding.isEnabled = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN_VERSION}")
    implementation("androidx.core:core-ktx:${Versions.ANDROIDX_CORE_KTS_VERSION}")
    implementation("androidx.appcompat:appcompat:${Versions.ANDROIDX_APPCOMPAT_VERSION}")
    implementation("androidx.fragment:fragment-ktx:${Versions.ANDROIDX_FRAGMENT_KTS_VERSION}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.ANDROIDX_CONSTRAINT_LAYOUT_VERSION}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ANDROIDX_LIFECYCLE_KTS_VERSION}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.ANDROIDX_LIFECYCLE_KTS_VERSION}")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLINX_COROUTINES_VERSION}")

    implementation("androidx.camera:camera-camera2:${Versions.CAMERAX_VERSION}")
    implementation("androidx.camera:camera-lifecycle:${Versions.CAMERAX_VERSION}")
    implementation("androidx.camera:camera-view:${Versions.CAMERA_VIEW_VERSION}")

    testImplementation("junit:junit:${Versions.JUNIT_VERSION}")
    androidTestImplementation("androidx.test.ext:junit:${Versions.ANDROIDX_JUNIT_VERSION}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.ESPRESSO_VERSION}")

}