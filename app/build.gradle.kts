plugins {
    id("com.android.application")
}

android {
    namespace = "com.gero.newpass"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gero.newpass"
        minSdk = 24
        targetSdk = 34
        versionCode = 12
        versionName = "1.1.10"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            //vcsInfo.include = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    /*
    this configuration is used to make the build output (APK or AAB) smaller by
    excluding information about the project's dependencies
     */
    android {
        dependenciesInfo {
            // Disables dependency metadata when building APKs.
            includeInApk = false
            // Disables dependency metadata when building Android App Bundles.
            includeInBundle = false
        }
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity:1.9.0")
    implementation("androidx.databinding:databinding-runtime:8.4.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.google.android.material:material:1.12.0")
    implementation("net.zetetic:android-database-sqlcipher:4.4.0")
    implementation("androidx.security:security-crypto:1.1.0-alpha06")
    implementation("androidx.biometric:biometric:1.1.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("com.google.android.material:material:1.12.0")
}