plugins {
//    alias(libs.plugins.android.application)
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    id("maven-publish") // Make sure this line is present
}

android {
    namespace = "com.example.appsample1"
    compileSdk = 35

    defaultConfig {
//        applicationId = "com.example.appsample1"
        minSdk = 24
        targetSdk = 35
//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
//            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        debug {
            isMinifyEnabled = false
//            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("profile") {
            initWith(getByName("debug"))
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
}

dependencies {
//    implementation(files("/Users/fauzanakmalmahdi/Documents/Main/Flutter Project/flutter_module1/build/host/outputs/repo/com/example/flutter_module1/flutter_release/1.0/flutter_release-1.0.aar"))
    debugImplementation("com.example.flutter_module1:flutter_debug:1.0")
    releaseImplementation("com.example.flutter_module1:flutter_release:1.0")
    add("profileImplementation", "com.example.flutter_module1:flutter_profile:1.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0") {
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
                groupId = "com.example.appsample1"
                artifactId = "konnek-android"
                version = "1.0.0"
            }
        }

        repositories {
            maven {
                println("Message rootProject: ${rootProject}")  // String interpolation
                println("Message rootDir: ${rootDir}")  // String interpolation
                println("Message rootProject: ${rootProject}")  // String interpolation
                println("Message rootProject.buildDir: ${rootProject.buildDir}")  // String interpolation
                println("Message rootProject.projectDir: ${rootProject.projectDir}")  // String interpolation
                url = uri("${rootProject.projectDir}/app/build")
            }
        }
    }
}

//This is works
//publishing {
//    publications {
//        create<MavenPublication>("release") {
//            groupId = "com.example.appsample1" // e.g., com.example
//            artifactId = "konnek-android"
//            version = "1.0.0" // Your library's version
//
//            // For AAR publication
//            artifact("$buildDir/outputs/aar/${project.name}-release.aar")
//        }
//    }
//    repositories {
//        maven {
//            url = uri("$rootDir/repository") // Define a local repository directory
//        }
//    }
//}