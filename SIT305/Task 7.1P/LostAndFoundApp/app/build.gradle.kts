plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.markheath.lostandfoundapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.markheath.lostandfoundapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    // https://developer.android.com/jetpack/androidx/releases/room#kts
    val room_version = "2.6.1"

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Room Persistence Library (modified to use the room_version var)
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // RxJava (for async database access) (see docs linked for room_version var)
    implementation("androidx.room:room-rxjava3:$room_version")
    // https://stackoverflow.com/questions/40690369/cannot-resolve-symbol-androidschedulers
    // stupidly need v2 for AndroidSchedulers
    implementation("androidx.room:room-rxjava2:$room_version")
}