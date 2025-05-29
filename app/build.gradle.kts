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
//        create("profile") {
//            initWith(getByName("debug"))
//        }
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
//    implementation(project(":flutter"))
//    debugImplementation("com.konneknative.core:flutter_debug:1.0")
//    releaseImplementation("com.konneknative.core:flutter_release:1.0")
    implementation("com.konneknative.core:flutter_release:1.0")
//    add("profileImplementation", "com.konneknative.core:flutter_profile:1.0")
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