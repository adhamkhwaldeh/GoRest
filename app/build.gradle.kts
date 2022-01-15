plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = "com.aljawad.sons.gorest"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.compileSdk)
        versionCode(AppConfig.versionCode)
        versionName(AppConfig.versionName)
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            applicationIdSuffix(".debug")
        }

        create("production") {
            applicationIdSuffix(".prod")
        }

        create("development") {
            applicationIdSuffix(".dev")
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }


    kapt {
        correctErrorTypes = true
    }
//    hilt {
//        enableAggregatingTask = true
//    }


//    hilt { enableAggregatingTask  = true }
//    packagingOptions {
//        exclude("META-INF/notice.txt")
//    }

//    packagingOptions {
//        exclude("META-INF/DEPENDENCIES")
//        exclude("META-INF/LICENSE")
//        exclude("META-INF/LICENSE.txt")
//        exclude("META-INF/license.txt")
//        exclude("META-INF/NOTICE")
//        exclude("META-INF/NOTICE.txt")
//        exclude("META-INF/notice.txt")
//        exclude("META-INF/ASL2.0")
//        exclude("META-INF/*.kotlin_module")
//        exclude("project.properties")
//        exclude("META-INF/INDEX.LIST")
//        exclude("META-INF/gradle/incremental.annotation.processors")
////        resources.excludes.add("META-INF/*")
//    }
}

dependencies {
    implementation(AppDependencies.coroutinesLibrary)
    implementation(AppDependencies.appLibraries)
    implementation(AppDependencies.retrofitLibraries)
    implementation(AppDependencies.pagingLibrary)
    //region Dagger Hilt
    implementation(AppDependencies.hiltLibrary)
    kapt(AppDependencies.hilAndroidCompiler)
    kapt(AppDependencies.hilCompiler)
    //endregion

    implementation(project(":goRestCore"))
    implementation(project(":goRestRepository"))
    implementation(project(":mainLibrary"))


//    testImplementation 'junit:junit:4.+'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
}

