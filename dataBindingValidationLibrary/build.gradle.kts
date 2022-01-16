plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
//    id("com.github.dcendents.android-maven")
    id("kotlin-parcelize")
}

group = "org.bitbucket.ilhasoft"



dependencies {
//    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation(AppDependencies.appLibraries)
    // JUnit 4 framework
//    testImplementation ("junit:junit:4.12")
    // Android annotations
//    androidTestImplementation ("androidx.annotation:annotation:1.0.0")
    // AndroidJUnitRunner and JUnit Rules
//    androidTestImplementation("androidx.test:runner:1.1.0-beta02")
//    androidTestImplementation ("androidx.test:rules:1.1.0-beta02")
}

// build a jar with source files
//task sourcesJar(type: Jar) {
//    from android.sourceSets.main.java.srcDirs
//    classifier = 'sources'
//}

//task my(type: Javadoc) {
//    failOnError false
//    source = android.sourceSets.main.java.sourceFiles
//    classpath += project.files(android.getBootClasspath().join(File.pathSeparator))
//    classpath += configurations.compile
//}

// build a jar with javadoc
//task javadocJar(type: Jar, dependsOn: javadoc) {
//    classifier = 'javadoc'
//    from javadoc.destinationDir
//}

//artifacts {
//    archives sourcesJar
//    archives javadocJar
//}

// uncomment to build a jar file in addition to the default aar file
//android.libraryVariants.all { variant ->
//    def name = variant.buildType.name
//    def task = project.tasks.create "jar${name.capitalize()}", Jar
//    task.dependsOn variant.javaCompile
//    task.from variant.javaCompile.destinationDir
//    artifacts.add('archives', task);