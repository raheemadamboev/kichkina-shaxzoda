plugins {
    alias(libs.plugins.android)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.gms)
    alias(libs.plugins.crashlytics)
    alias(libs.plugins.hilt)
}

android {
    namespace = "xyz.teamgravity.kichkinashahzoda"
    compileSdk = libs.versions.sdk.compile.get().toInt()

    defaultConfig {
        applicationId = "xyz.teamgravity.kichkinashahzoda"
        minSdk = libs.versions.sdk.min.get().toInt()
        targetSdk = libs.versions.sdk.target.get().toInt()
        versionCode = 5
        versionName = "1.1.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        ndk {
            debugSymbolLevel = "FULL"
            abiFilters.addAll(setOf("armeabi-v7a", "arm64-v8a", "x86_64", "x86"))
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        debug {
            applicationIdSuffix = ".debug"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = libs.versions.java.target.get()
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // compose
    implementation(platform(libs.compose))
    implementation(libs.compose.ui)
    implementation(libs.compose.graphics)
    implementation(libs.compose.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.icons)

    // compose constraint layout
    implementation(libs.compose.constraintLayout)

    // compose activity
    implementation(libs.compose.activity)

    // compose lifecycle
    implementation(libs.compose.lifecycle)

    // compose viewmodel
    implementation(libs.compose.viewmodel)

    // compose hilt
    implementation(libs.compose.hilt)

    // core
    implementation(libs.core)

    // collections
    implementation(libs.collections)

    // firebase
    implementation(platform(libs.firebase))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.messaging)

    // hilt
    implementation(libs.hilt)
    ksp(libs.dagger.compiler)

    // coroutines
    implementation(libs.coroutines)
    implementation(libs.coroutines.android)

    // exoplayer
    implementation(libs.exoplayer)
    implementation(libs.exoplayer.ui)
    implementation(libs.exoplayer.mediasession)

    // destinations
    implementation(libs.destinations)
    ksp(libs.destinations.compiler)

    // timber
    implementation(libs.timber)
}

allprojects {
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
        kotlinOptions {
            freeCompilerArgs += listOf(
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
            )
        }
    }
}