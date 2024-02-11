plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlin.serialization)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "ru.glebik.feature.home.api"
    compileSdk = libs.versions.compileSdk.get().toInt()

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    api(project(":core:widget"))
    api(project(":core:utils"))
    //circular dependency
//    api(project(":core:db"))
    api(project(":core:network"))
    api(project(":core:navigation"))
    api(project(":core:presentation"))

    api(libs.koin)
    api(libs.koin.compose)

    api(libs.room)
    api(libs.room)
    kapt(libs.room.kapt)

    api(libs.kotlinx.serialization.json)
    api(libs.ktor.serialization)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}