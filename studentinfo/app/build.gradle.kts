plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.studentinfo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.studentinfo"
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
}

dependencies {
    // Core dependencies for the StudentInfo app
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)

    // Optional: Material for enhanced UI (remove if not needed)
    implementation(libs.material)

    // Testing dependencies (keep as-is)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}