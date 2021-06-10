import Versions.chuckVersion
import Versions.daggerVersion
import Versions.glideVersion
import Versions.junitVersion
import Versions.kotlinVersion
import Versions.leakCanaryVersion
import Versions.lifecycleVersion
import Versions.moshiVersion
import Versions.navigationVersion
import Versions.okhttpVersion
import Versions.pagingVersion
import Versions.retrofitVersion

@Suppress("MayBeConstant", "MemberVisibilityCanBePrivate", "unused")
object Dependencies {

    // AndroidX
    val appcompat = "androidx.appcompat:appcompat:1.2.0"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
    val lifecycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion"
    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    val material = "com.google.android.material:material:1.2.0"
    val multidex = "androidx.multidex:multidex:2.0.1"
    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
    val paging = "androidx.paging:paging-runtime:$pagingVersion"
    val pagingRxJava = "androidx.paging:paging-rxjava3:$pagingVersion"
    val preference = "androidx.preference:preference-ktx:1.1.1"
    val recyclerview = "androidx.recyclerview:recyclerview:1.2.1"
    val security = "androidx.security:security-crypto-ktx:1.1.0-alpha02"

    val firebaseBom = "com.google.firebase:firebase-bom:28.1.0"
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
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinVersion"

    // Dagger
    val javax = "javax.inject:javax.inject:1"
    val annotationsApi = "javax.annotation:javax.annotation-api:1.2"
    val dagger = "com.google.dagger:dagger:$daggerVersion"
    val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"

    // Glide
    val glide = "com.github.bumptech.glide:glide:$glideVersion"
    val glideOkHttp = "com.github.bumptech.glide:okhttp3-integration:$glideVersion"
    val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"

    // Libraries
    val timber = "com.jakewharton.timber:timber:4.7.1"
    val chuck = "com.readystatesoftware.chuck:library:$chuckVersion"
    val chuckNoOp = "com.readystatesoftware.chuck:library-no-op:$chuckVersion"
    val inject = "javax.inject:javax.inject:1"
    val leakCanary = "com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion"
    val leakCanaryNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:$leakCanaryVersion"
    val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:1.0.10"

    // Testing
    val jUnit = "junit:junit:$junitVersion"
    val mockito = "org.mockito:mockito-core:3.11.0"
    val hamcrest = "org.hamcrest:hamcrest-all:1.3"

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
        navigationUi,
        security
    )

    val testLibs = listOf(jUnit, mockito, hamcrest)

    val featureProjects = listOf(":auth", ":home", ":show")
}
