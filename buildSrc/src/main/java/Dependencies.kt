import Versions.archVersion
import Versions.chuckVersion
import Versions.daggerVersion
import Versions.glideVersion
import Versions.junitVersion
import Versions.kotlinVersion
import Versions.leakCanaryVersion
import Versions.moshiVersion
import Versions.okhttpVersion
import Versions.playVersion
import Versions.retrofitVersion
import Versions.rxandroidVersion
import Versions.rxbindingVersion
import Versions.rxjavaVersion
import Versions.supportLibraryVersion

@Suppress("MayBeConstant", "MemberVisibilityCanBePrivate", "unused")
object Dependencies {

    // Support Libraries
    val appcompatV7 = "com.android.support:appcompat-v7:$supportLibraryVersion"
    val design = "com.android.support:design:$supportLibraryVersion"
    val recyclerviewV7 = "com.android.support:recyclerview-v7:$supportLibraryVersion"
    val supportAnnotations = "com.android.support:support-annotations:$supportLibraryVersion"
    val supportV4 = "com.android.support:support-v4:$supportLibraryVersion"
    val cardView = "com.android.support:cardview-v7:$supportLibraryVersion"
    val constraintLayout = "com.android.support.constraint:constraint-layout:2.0.2"
    val multidex = "com.android.support:multidex:1.0.3"

    // AndroidX
    val androidXpreference = "androidx.preference:preference:1.1.1"

    // Firebase
    val firebaseConfig = "com.google.firebase:firebase-config:$playVersion"
    val firebaseCore = "com.google.firebase:firebase-core:17.5.1"
    val firebaseCrash = "com.google.firebase:firebase-crash:16.2.1"
    val firebasePerf = "com.google.firebase:firebase-perf:19.0.9"
    val firebaseMessaging = "com.google.firebase:firebase-messaging:$playVersion"

    // Architecture Components
    val archCompiler = "android.arch.lifecycle:compiler:$archVersion"
    val archEx = "android.arch.lifecycle:extensions:$archVersion"
    val archJava = "android.arch.lifecycle:common-java8:$archVersion"
    val archRuntime = "android.arch.lifecycle:runtime:$archVersion"
    val archViewModel = "android.arch.lifecycle:viewmodel:$archVersion"

    // Api
    val okio = "com.squareup.okio:okio:2.9.0"
    val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"
    val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    val converterMoshi = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    val adapterRxjava = "com.squareup.retrofit2:adapter-rxjava3:$retrofitVersion"
    val moshi = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"

    // Kotlin
    val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"

    // Dagger
    val javax = "javax.inject:javax.inject:1"
    val annotationsApi = "javax.annotation:javax.annotation-api:1.2"
    val dagger = "com.google.dagger:dagger:$daggerVersion"
    val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"

    // Rx Bindings
    const val rxbindingGroupId = "com.jakewharton.rxbinding4"
    val rxbinding = "$rxbindingGroupId:rxbinding-core:$rxbindingVersion"
    val rxbindingAppcompatV7 = "$rxbindingGroupId:rxbinding-appcompat:$rxbindingVersion"
    val rxbindingRecyclerview = "$rxbindingGroupId:rxbinding-recyclerview:$rxbindingVersion"
    val rxbindingMaterial = "$rxbindingGroupId:rxbinding-material:$rxbindingVersion"

    // Glide
    val glide = "com.github.bumptech.glide:glide:$glideVersion"
    val glideOkHttp = "com.github.bumptech.glide:okhttp3-integration:$glideVersion"
    val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"

    // Libraries
    val rxjava = "io.reactivex.rxjava3:rxjava:$rxjavaVersion"
    val rxandroid = "io.reactivex.rxjava3:rxandroid:$rxandroidVersion"
    val timber = "com.jakewharton.timber:timber:4.7.1"
    val chuck = "com.readystatesoftware.chuck:library:$chuckVersion"
    val chuckNoOp = "com.readystatesoftware.chuck:library-no-op:$chuckVersion"
    val inject = "javax.inject:javax.inject:1"
    val leakCanary = "com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion"
    val leakCanaryNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:$leakCanaryVersion"
    val pageIndicator = "com.github.chahinem:pageindicator:0.2.4"

    // Testing
    val jUnit = "junit:junit:$junitVersion"
    val mockito = "org.mockito:mockito-core:3.5.13"
    val hamcrest = "org.hamcrest:hamcrest-all:1.3"

    // Dependency Group
    val rx = listOf(rxjava, rxandroid)

    val rxBindings = listOf(
        rxbinding,
        rxbindingAppcompatV7,
        rxbindingRecyclerview,
        rxbindingMaterial
    )

    val api = listOf(
        retrofit,
        converterMoshi,
        adapterRxjava,
        okhttp,
        loggingInterceptor,
        okio,
        moshi
    )

    val arch = listOf(archEx, archJava, archRuntime)

    val archAP = listOf(archCompiler)

    val supportLibs = listOf(
        appcompatV7,
        cardView,
        constraintLayout,
        design,
        multidex,
        recyclerviewV7,
        supportAnnotations,
        supportV4
    )

    val firebase = listOf(
        firebaseCore,
        firebaseCrash,
        firebasePerf
    )

    val testLibs = listOf(jUnit, mockito, hamcrest)

    val featureProjects = listOf(":auth", ":home", ":show")
}
