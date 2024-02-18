plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "kom.apnawallet.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "kom.apnawallet.myapplication"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        viewBinding = true
    }
    repositories {
        google()
        mavenCentral()
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
    packagingOptions {
        exclude ("META-INF/LICENSE.md")
        exclude("META-INF/LICENSE-notice.md")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.android.car.ui:car-ui-lib:2.6.0")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    implementation("androidx.test.espresso:espresso-contrib:3.5.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation ("org.mockito:mockito-core:3.12.4")
    testImplementation ("com.google.truth:truth:1.1.3")
    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    testImplementation ("org.jetbrains.kotlin:kotlin-test-junit:1.8.22")
    testImplementation ("org.robolectric:robolectric:4.6.1")
    testImplementation ("io.mockk:mockk:1.12.0")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")
    androidTestImplementation("androidx.test:core-ktx:1.5.0")
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")


    // Coroutine testing
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    // MockWebServer for testing Retrofit
    testImplementation ("com.squareup.okhttp3:mockwebserver:4.9.1")

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    //GSON
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //coroutine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

    //lifecycle
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.11.0")
    implementation ("com.github.bumptech.glide:compiler:4.11.0")

    //SwipeRefreshView
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    //Shimmer
    implementation ("com.facebook.shimmer:shimmer:0.5.0")
    androidTestImplementation("org.junit.jupiter:junit-jupiter:5.8.1")

    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

}