import Versions.chuckVersion
import Versions.daggerVersion
import Versions.glideVersion
import Versions.junitVersion
import Versions.kotlinVersion
import Versions.leakCanaryVersion
import Versions.lifecycleVersion
import Versions.moshiVersion
import Versions.navVersion
import Versions.okhttpVersion
import Versions.retrofitVersion
import Versions.rxandroidVersion
import Versions.rxbindingVersion
import Versions.rxjavaVersion

@Suppress("MayBeConstant", "MemberVisibilityCanBePrivate", "unused")
object Dependencies {

    // AndroidX
    val appcompat = "androidx.appcompat:appcompat:1.2.0"
    val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    val multidex = "androidx.multidex:multidex:2.0.1"
    val preference = "androidx.preference:preference-ktx:1.1.1"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
    val lifecycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion"
    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    val material = "com.google.android.material:material:1.2.0"
    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navVersion"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:$navVersion"

    val firebaseBom = "com.google.firebase:firebase-bom:26.0.0"
    val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    val firebasePerf = "com.google.firebase:firebase-perf-ktx"

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
    val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:1.0.10"

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

    val androidX = listOf(
        appcompat,
        recyclerview,
        constraintLayout,
        multidex,
        preference,
        material,
        lifecycleCompiler,
        lifecycleExtensions,
        lifecycleCommonJava8,
        lifecycleRuntime,
        lifecycleViewmodel,
        lifecycleLivedata,
        material,
        navigationFragment,
        navigationUi
    )

    val testLibs = listOf(jUnit, mockito, hamcrest)

    val featureProjects = listOf(":auth", ":home", ":show")
}
