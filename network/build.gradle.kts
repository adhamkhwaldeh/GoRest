plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

//android {
//    compileSdkVersion 31
//    buildToolsVersion "30.0.3"
//
//    defaultConfig {
//        minSdkVersion 21
//        targetSdkVersion 31
//        versionCode 1
//        versionName "1.0"
//
//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles "consumer-rules.pro"
//    }
//
//    buildTypes {
//        release {
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//    }
//    compileOptions {
//        sourceCompatibility JavaVersion.VERSION_1_8
//        targetCompatibility JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = '1.8'
//    }
//}

dependencies {
    implementation(AppDependencies.appLibraries)
    implementation(AppDependencies.retrofitLibraries)

    //region Dagger Hilt
    implementation(AppDependencies.hiltLibrary)
    kapt(AppDependencies.hilAndroidCompiler)
    kapt(AppDependencies.hilCompiler)
    //endregion

    implementation(project(":dtos"))

}