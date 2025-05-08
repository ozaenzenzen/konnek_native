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
//    publishing {
//        publications {
//            create<MavenPublication>("release") {
//                from(components["release"])
//                groupId = "com.example.appsample1"
//                artifactId = "konnek-android"
//                version = "1.0.0"
//            }
//        }
////        publications {
////            create<MavenPublication>("release") {
////                from(components["release"])
////
////                // Add dependency information to POM file
////                pom {
////                    withXml {
////                        val dependenciesNode = asNode().appendNode("dependencies")
////
////                        // Add all implementation dependencies
////                        configurations["implementation"].allDependencies.forEach {
////                            if (it.name != "unspecified") {
////                                val dependencyNode = dependenciesNode.appendNode("dependency")
////                                dependencyNode.appendNode("groupId", it.group)
////                                dependencyNode.appendNode("artifactId", it.name)
////                                dependencyNode.appendNode("version", it.version)
////                            }
////                        }
////                    }
////                }
////            }
////        }
//        repositories {
//            maven {
//                url = uri("$buildDir/build/repo")
//            }
//        }
//    }
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
                url = uri("${rootProject.buildDir}/local-maven")
//                url = uri("${rootProject.buildDir}/app/build")
//                url = uri("${rootProject.buildDir}")
            }
        }
    }
}

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

//publishing {
//    publications {
//        create<MavenPublication>("release") {
//            groupId = "com.example.appsample1" // e.g., com.example
//            artifactId = "konnek-android"
//            version = "1.0.0" // Your library's version
//
////            // Directly reference your AAR file
//            artifact("$buildDir/outputs/aar/app-release.aar")
////            artifact("$buildDir/outputs/aar/file.aar")
//        }
//    }
//    repositories {
//        maven {
//            url = uri("$rootDir/repository") // Define a local repository directory
//        }
//    }
//}

//publishing {
//    publications {
//        create<MavenPublication>("release") {
//            from(components["release"])
//            groupId = "com.example.appsample1"
//            artifactId = "konnek-android"
//            version = "1.0.0"
//        }
//    }
//    repositories {
//        maven {
//            url = uri("$buildDir/repo")
//        }
//    }
//}

//val runtimeClasspath by configurations
//
//tasks.register<Copy>("bundleExternalLibs") {
//    from(runtimeClasspath.filter { it.name.endsWith(".jar") })
//    into("$buildDir/intermediates/aar_main_jar/libs/")
//}
//
//afterEvaluate {
//    tasks.named("assembleRelease").configure {
//        dependsOn("bundleExternalLibs")
//    }
//
//    publishing {
//        publications {
//            create<MavenPublication>("release") { // You can name this publication as you like
//                from(components["assembleRelease"]) // Or "debug" if you want to publish debug builds
//
////            groupId = "your.group.id" // Replace with your desired group ID
////            artifactId = "your-library-name" // Replace with your library's artifact ID
////            version = "1.0.0" // Replace with your library's version
//                groupId = "com.example.appsample1" // Replace with your desired group ID
//                artifactId = "konnek-native" // Replace with your library's artifact ID
//                version = "1.0.0" // Replace with your library's version
//            }
//        }
//
//        repositories {
//            maven {
//                url = uri("$buildDir/repo") // Example: Publish to a local repository for testing
//                // In a real scenario, you'd configure a remote Maven repository (e.g., Maven Central, JCenter, a private repository)
//            }
//        }
//    }
//}