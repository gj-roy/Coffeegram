import com.google.protobuf.gradle.*

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.protobuf") version "0.8.17"
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "ru.beryukhov.coffeegram"
        minSdk = 21
        targetSdk = 30
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
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        //useIR = true
        freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-rc02"//libs.versions.compose.get()
    }
    packagingOptions {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

dependencies {

    implementation(projects.repository)
    implementation(projects.appWearCommon)

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.4.0")

    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.uiTooling)

    implementation(libs.compose.constraint)
    implementation(libs.compose.activity)

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation(libs.compose.uiTest)
    androidTestImplementation(libs.compose.uiTestJunit4)


    implementation("com.jakewharton.threetenabp:threetenabp:1.3.1")

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.datastore.preferences)

    implementation  (libs.datastore.datastore)
    implementation  ("com.google.protobuf:protobuf-javalite:3.10.0")

    implementation(libs.koin.android)

}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.10.0"
    }
    generateProtoTasks {
        all().forEach{
                task ->
            task.builtins {
                id("java") {
                    option("lite")
                }
            }
        }

    }
}
