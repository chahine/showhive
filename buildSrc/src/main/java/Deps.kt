import Versions.archVersion
import Versions.chuckVersion
import Versions.daggerVersion
import Versions.junitVersion
import Versions.kotlinVersion
import Versions.leakCanaryVersion
import Versions.okhttpVersion
import Versions.playVersion
import Versions.retrofitVersion
import Versions.rxandroidVersion
import Versions.rxbindingVersion
import Versions.rxjavaVersion
import Versions.supportLibraryVersion

@Suppress("MayBeConstant", "MemberVisibilityCanBePrivate")
object Deps {

  // Support Libraries
  val appcompatV7 = "com.android.support:appcompat-v7:$supportLibraryVersion"
  val design = "com.android.support:design:$supportLibraryVersion"
  val recyclerviewV7 = "com.android.support:recyclerview-v7:$supportLibraryVersion"
  val supportAnnotations = "com.android.support:support-annotations:$supportLibraryVersion"
  val supportV4 = "com.android.support:support-v4:$supportLibraryVersion"
  val cardView = "com.android.support:cardview-v7:$supportLibraryVersion"
  val customtabs = "com.android.support:customtabs:$supportLibraryVersion"
  val constraintLayout = "com.android.support.constraint:constraint-layout:1.1.0-beta5"
  val multidex = "com.android.support:multidex:1.0.2"

  // Firebase
  val firebaseConfig = "com.google.firebase:firebase-config:$playVersion"
  val firebaseCore = "com.google.firebase:firebase-core:$playVersion"
  val firebaseCrash = "com.google.firebase:firebase-crash:$playVersion"
  val firebasePerf = "com.google.firebase:firebase-perf:$playVersion"
  val firebaseMessaging = "com.google.firebase:firebase-messaging:$playVersion"

  // Architecture Components
  val archEx = "android.arch.lifecycle:extensions:$archVersion"
  val archCompiler = "android.arch.lifecycle:compiler:$archVersion"
  val archJava = "android.arch.lifecycle:common-java8:$archVersion"

  // Api
  val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
  val moshi = "com.squareup.moshi:moshi-kotlin:1.5.0"
  val converterMoshi = "com.squareup.retrofit2:converter-moshi:2.3.0"
  val adapterRxjava = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
  val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
  val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"
  val okio = "com.squareup.okio:okio:1.14.0"

  // Kotlin
  val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jre8:$kotlinVersion"

  // Dagger
  const val annotationsApi = "javax.annotation:javax.annotation-api:1.2"
  val dagger = "com.google.dagger:dagger:$daggerVersion"
  val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
  val daggerAndroidSupport = "com.google.dagger:dagger-android-support:$daggerVersion"
  val daggerAndroidCompiler = "com.google.dagger:dagger-android-processor:$daggerVersion"

  // Rx Bindings
  const val rxbindingGroupId = "com.jakewharton.rxbinding2"
  val rxbinding = "$rxbindingGroupId:rxbinding-kotlin:$rxbindingVersion"
  val rxbindingSupportV4 = "$rxbindingGroupId:rxbinding-support-v4-kotlin:$rxbindingVersion"
  val rxbindingAppcompatV7 = "$rxbindingGroupId:rxbinding-appcompat-v7-kotlin:$rxbindingVersion"
  val rxbindingDesign = "$rxbindingGroupId:rxbinding-design-kotlin:$rxbindingVersion"
  val rxbindingRecyclerview = "$rxbindingGroupId:rxbinding-recyclerview-v7:$rxbindingVersion"

  // Libraries
  val picasso = "com.squareup.picasso:picasso:2.6.0-SNAPSHOT"
  val rxjava = "io.reactivex.rxjava2:rxjava:$rxjavaVersion"
  val rxandroid = "io.reactivex.rxjava2:rxandroid:$rxandroidVersion"
  val patrons = "com.prolificinteractive:patrons:0.1.0"
  val conceal = "com.facebook.conceal:conceal:2.0.2@aar"
  val timber = "com.jakewharton.timber:timber:4.6.1"
  val chuck = "com.readystatesoftware.chuck:library:$chuckVersion"
  val chuckNoOp = "com.readystatesoftware.chuck:library-no-op:$chuckVersion"
  val threetenabp = "com.jakewharton.threetenabp:threetenabp:1.0.5"
  val inject = "javax.inject:javax.inject:1"
  val leakCanary = "com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion"
  val leakCanaryNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:$leakCanaryVersion"
  val pageIndicator = "com.github.chahinem:pageindicator:0.2.4"

  // Testing
  val jUnit = "junit:junit:$junitVersion"
  val mockito = "org.mockito:mockito-core:2.15.0"
  val hamcrest = "org.hamcrest:hamcrest-all:1.3"

  // Dependency Group
  val rx = listOf(rxjava, rxandroid)

  val rxBindings = listOf(
      rxbinding,
      rxbindingSupportV4,
      rxbindingAppcompatV7,
      rxbindingDesign,
      rxbindingRecyclerview
  )

  val api = listOf(
      retrofit,
      converterMoshi,
      adapterRxjava,
      okhttp,
      loggingInterceptor,
      okio,
      moshi)

  val arch = listOf(archEx, archJava)

  val archAP = listOf(archCompiler)

  val supportLibs = listOf(appcompatV7,
      cardView,
      constraintLayout,
      customtabs,
      design,
      multidex,
      recyclerviewV7,
      supportAnnotations,
      supportV4
  )

  val firebase = listOf(
      firebaseConfig,
      firebaseCore,
      firebaseCrash,
      firebasePerf,
      firebaseMessaging)

  val testLibs = listOf(jUnit, mockito, hamcrest)

  val featureProjects = listOf(":auth", ":home")
}
