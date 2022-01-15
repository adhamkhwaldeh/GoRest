plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}


dependencies {
    implementation(AppDependencies.appLibraries)
    implementation(AppDependencies.retrofitLibraries)
    implementation(AppDependencies.pagingLibrary)

    //region Dagger Hilt
    implementation(AppDependencies.hiltLibrary)
    kapt(AppDependencies.hilAndroidCompiler)
    kapt(AppDependencies.hilCompiler)
    //endregion

    implementation(project(":dtos"))

    implementation(project(":goRestRepository"))
    implementation(project(":mainLibrary"))
    implementation(project(":business"))


//    testImplementation 'junit:junit:4.+'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
}