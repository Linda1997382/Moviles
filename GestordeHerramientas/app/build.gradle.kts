plugins {
    alias(libs.plugins.android.application)  // Cambiado a la forma recomendada
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)         // Para Room
}

android {
    namespace = "com.taller.gestordeherramientas"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.taller.gestordeherramientas"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"  // Nota: Hay un typo en "runner"
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

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"  // Correcto para composeBom 2023.10.01
    }
}

dependencies {
    // AndroidX Core y Lifecycle
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Activity Compose
    implementation(libs.androidx.activity.compose)

    // Compose BOM (Bill of Materials)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    androidTestImplementation(platform(libs.androidx.compose.bom))  // Para tests

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    // Corrutinas
    implementation(libs.kotlinx.coroutines.android)

    // Imágenes con Coil
    implementation(libs.coil.compose)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)  // Corregido el nombre
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)  // Corregido el nombre

    // Debugging Compose
    debugImplementation(libs.androidx.compose.ui.tooling)  // Corregido el nombre
    debugImplementation(libs.androidx.compose.ui.test.manifest)  // Corregido el nombre
}