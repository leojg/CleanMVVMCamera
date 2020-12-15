buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.BUILD_TOOLS_VERSION}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_VERSION}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT_VERSION}")

    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}