plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
    id("kotlin-parcelize")
}

android {
    namespace = "com.konneknative"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        targetSdk = 35

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        debug {
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
        viewBinding = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation("com.konneknative.core:flutter_release:1.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0") {
        isTransitive = true
    }
    api("com.squareup.retrofit2:retrofit:2.9.0") {
        isTransitive = true
    }
    api("com.squareup.okhttp3:logging-interceptor:4.8.0") {
        isTransitive = true
    }
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.konneknative"
                artifactId = "konnek-android"
                version = "1.0.0"
            }
            create<MavenPublication>("development") {
                from(components["release"])
                groupId = "com.konneknative"
                artifactId = "konnek-android"
                version = "1.0.0"
            }
        }

        repositories {
            maven {
                url = uri("${rootProject.projectDir}/app/build")
            }
        }
    }
}