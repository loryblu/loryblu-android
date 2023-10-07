plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}


android {
    namespace = "com.loryblu.loryblu"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = "com.loryblu.loryblu"
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = ProjectConfig.kotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes +=("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(platform(libs.kotlin.bom))
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.navigation.compose)
    implementation(libs.koin.androidx.compose)
    implementation(libs.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.ui.test.junit4)

    implementation(project(":core:ui"))
    implementation(project(":core:util"))
    implementation(project(":feature:auth:login"))
    implementation(project(":feature:auth:register"))
    implementation(project(":feature:auth:forgot_password"))
    implementation(project(":feature:auth:create_password"))
    implementation(project(":feature:home"))
    implementation(project(":data:auth"))
    implementation(project(":core:network"))
}