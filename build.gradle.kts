plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.japaneselearning"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.japaneselearning"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportVector = true
        }
    }

    signingConfigs {
        create("release") {
            val storeFile = System.getenv("KEYSTORE_FILE")?.let { file(it) }
            val storePassword = System.getenv("KEYSTORE_PASSWORD")
            val keyAlias = System.getenv("KEY_ALIAS")
            val keyPassword = System.getenv("KEY_PASSWORD")

            if (storeFile != null && storePassword != null && keyAlias != null && keyPassword != null) {
                storeFile = storeFile
                storePassword = storePassword
                keyAlias = keyAlias
                keyPassword = keyPassword
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,license.txt,NOTICE}"
        }
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.04.01")
    val material3 = "androidx.compose.material3:material3"
    val activityCompose = "androidx.activity:activity-compose:1.9.0"
    val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0"
    val navigationCompose = "androidx.navigation:navigation-compose:2.7.7"
    val room = "androidx.room:room-runtime:2.6.1"
    val roomKtx = "androidx.room:room-ktx:2.6.1"
    val roomCompiler = "androidx.room:room-compiler:2.6.1"
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0"
    val coil = "io.coil-kt:coil-compose:2.5.0"
    val accompanistPager = "com.google.accompanist:accompanist-pager:0.32.0"

    implementation(composeBom)
    implementation(material3)
    implementation(activityCompose)
    implementation(viewModelCompose)
    implementation(navigationCompose)
    implementation(room)
    implementation(roomKtx)
    kapt(roomCompiler)
    implementation(coroutines)
    implementation(coil)
    implementation(accompanistPager)
}