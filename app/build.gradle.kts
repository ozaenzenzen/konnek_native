plugins {
//    alias(libs.plugins.android.application)
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    id("maven-publish") // Make sure this line is present
    id("kotlin-parcelize")
}

android {
    // namespace = "com.example.appsample1"
    namespace = "com.konneknative"
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
    debugImplementation("com.konneknative.core:flutter_debug:1.0")
    releaseImplementation("com.konneknative.core:flutter_release:1.0")
    add("profileImplementation", "com.konneknative.core:flutter_profile:1.0")
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

val splitPOM: MavenPom.() -> Unit = {
    name.set("Konnek Native")
    packaging = "aar"
    description.set("Official Konnek Android SDK")
    // url.set("https://github.com/splitio/android-client")

    licenses {
        license {
            name.set("The Apache License, Version 2.0")
            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
        }
    }

    developers {
        developer {
            id.set("ozaenzenzen")
            name.set("Fauzan Akmal Mahdi")
            email.set("fauzan.akmal@sprintasia.co.id")
        }
    }

    scm {
        connection.set("scm:git:git@github.com:ozaenzenzen/konnek_native.git")
        developerConnection.set("scm:git@github.com:ozaenzenzen/konnek_native.git")
        url.set("https://github.com/ozaenzenzen/konnek_native.git")
    }
}


//val sourcesJar by tasks.registering(Jar::class) {
//    archiveClassifier.set("sources")
//    from(android.sourceSets["main"].java.srcDirs)
//}
//
//val javadocJar by tasks.registering(Jar::class) {
//    archiveClassifier.set("javadoc")
//    from(tasks["javadoc"])
//}

//val generateRepo by tasks.register<Zip>("generateRepo") {
//    val publishTask = tasks.named(
//        "publishReleasePublicationToMyrepoRepository",
//        PublishToMavenRepository::class.java
//    )
//    from(publishTask.map { it.repository.url })
//    into("mylibrary")
//    archiveFileName.set("mylibrary.zip")
//}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
//                groupId = "com.example.appsample1"
                groupId = "com.konneknative"
                artifactId = "konnek-android"
                version = "1.0.0"

//                artifact(sourcesJar.get())
//                artifact(javadocJar.get())
//                artifact(generateRepo)

//                tasks {
//                    generateRepo
//                }

//                pom {
//                    splitPOM
//                }

                // artifact("${rootProject.projectDir}/app/build/entou/${project.name}-release.aar")
                // artifact("$buildDir/outputs/aar/${project.name}-release.aar")
                // artifact("$buildDir/outputs/aar/app-release.aar")
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
                println("Message buildDir: ${buildDir}")  // String interpolation
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