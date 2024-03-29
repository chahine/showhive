import Versions.chuckVersion
import Versions.composeVersion
import Versions.daggerVersion
import Versions.glideVersion
import Versions.junitVersion
import Versions.kotlinVersion
import Versions.leakCanaryVersion
import Versions.lifecycleVersion
import Versions.lottieVersion
import Versions.moshiVersion
import Versions.navigationVersion
import Versions.okhttpVersion
import Versions.pagingVersion
import Versions.retrofitVersion

@Suppress("MayBeConstant", "MemberVisibilityCanBePrivate", "unused")
object Dependencies {

    // AndroidX
    val appcompat = "androidx.appcompat:appcompat:1.4.1"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:2.2.0-alpha01"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:2.2.0"
    val lifecycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion"
    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    val material = "com.google.android.material:material:1.7.0"
    val multidex = "androidx.multidex:multidex:2.0.1"
    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
    val paging = "androidx.paging:paging-runtime:$pagingVersion"
    val preference = "androidx.preference:preference-ktx:1.2.0"
    val recyclerview = "androidx.recyclerview:recyclerview:1.3.0-alpha02"
    val security = "androidx.security:security-crypto-ktx:1.1.0-alpha03"

    // Compose
    val composeMaterial = "androidx.compose.material3:material3:1.0.0"
    val composeFoundation = "androidx.compose.foundation:foundation:$composeVersion"
    val composeRuntime = "androidx.compose.runtime:runtime:$composeVersion"
    val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    val composeUiTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    val composeActivity = "androidx.activity:activity-compose:1.6.1"
    val composeLifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"

    // Firebase
    val firebaseBom = "com.google.firebase:firebase-bom:30.0.2"
    val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    val firebasePerf = "com.google.firebase:firebase-perf-ktx"

    // Api
    val okio = "com.squareup.okio:okio:2.9.0"
    val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"
    val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    val converterMoshi = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    val moshi = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"

    // Kotlin
    val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1"

    // Dagger
    val javax = "javax.inject:javax.inject:1"
    val annotationsApi = "javax.annotation:javax.annotation-api:1.2"
    val dagger = "com.google.dagger:dagger:$daggerVersion"
    val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    val hilt = "com.google.dagger:hilt-android:$daggerVersion"
    val hiltCompiler = "com.google.dagger:hilt-compiler:$daggerVersion"

    // Glide
    val glide = "com.github.bumptech.glide:glide:$glideVersion"
    val glideOkHttp = "com.github.bumptech.glide:okhttp3-integration:$glideVersion"
    val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"
    val glideCompose = "com.github.bumptech.glide:compose:1.0.0-alpha.1"

    // Libraries
    val timber = "com.jakewharton.timber:timber:4.7.1"
    val chuck = "com.readystatesoftware.chuck:library:$chuckVersion"
    val chuckNoOp = "com.readystatesoftware.chuck:library-no-op:$chuckVersion"
    val inject = "javax.inject:javax.inject:1"
    val leakCanary = "com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion"
    val leakCanaryNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:$leakCanaryVersion"
    val lottie = "com.airbnb.android:lottie:$lottieVersion"
    val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:1.1.5"

    // Testing
    val jUnit = "junit:junit:$junitVersion"
    val mockito = "org.mockito:mockito-core:3.11.0"
    val hamcrest = "org.hamcrest:hamcrest-all:1.3"
    val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:3.2.0"
    val mockitoInline = "org.mockito:mockito-inline:2.28.2"
    val mockk = "io.mockk:mockk:1.10.6"
    val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0"

    val compose = listOf(
        composeMaterial,
        composeFoundation,
        composeRuntime,
        composeUiToolingPreview,
        composeActivity,
        composeLifecycleViewmodel,
    )

    val api = listOf(
        retrofit,
        converterMoshi,
        okhttp,
        loggingInterceptor,
        okio,
        moshi
    )

    val androidX = listOf(
        appcompat,
        recyclerview,
        constraintLayout,
        lifecycleCompiler,
        lifecycleExtensions,
        lifecycleCommonJava8,
        lifecycleRuntime,
        lifecycleViewmodel,
        lifecycleLivedata,
        material,
        multidex,
        navigationFragment,
        navigationUi,
        preference,
        security
    )

    val testLibs = listOf(
        jUnit,
        mockito,
        hamcrest,
        mockitoKotlin,
        mockitoInline,
        mockk,
        coroutinesTest
    )

    val featureProjects = listOf(":features:auth", ":features:home", ":features:show")
}
