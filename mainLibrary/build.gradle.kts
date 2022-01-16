plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
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
    implementation(AppDependencies.customLibs)

    implementation(AppDependencies.retrofitLibraries)

    implementation(project(":dtos"))

//    implementation 'androidx.core:core-ktx:1.7.0'
//    implementation 'androidx.appcompat:appcompat:1.4.0'
//    implementation 'com.google.android.material:material:1.4.0'
//
//    implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"
//    implementation "androidx.lifecycle:lifecycle-viewmodel-savedstate:2.2.0"
//    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.2.0"
//    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"
//    implementation "androidx.lifecycle:lifecycle-common-java8:2.2.0"
//
//    testImplementation 'junit:junit:4.+'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
}