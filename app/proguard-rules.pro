-dontwarn java.lang.invoke.*

-keepattributes *Annotation*
-keep class javax.inject.** { *; }

# Retrofit 2.X
## https://square.github.io/retrofit/ ##
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8
-dontwarn okio.**
-dontwarn javax.annotation.**
-keepattributes Signature
-keepattributes Exceptions

# Missing annotations are harmless.
-dontwarn sun.misc**
-dontwarn javax.annotation.**

-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

# Remove Log
-assumenosideeffects class android.util.Log {
  public static boolean isLoggable(java.lang.String, int);
  public static *** v(...);
  public static *** i(...);
  public static *** w(...);
  public static *** d(...);
  public static *** e(...);
}

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep class com.bumptech.glide.GeneratedAppGlideModuleImpl
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-keep public class com.chahine.trakt.api.entities.** {
  public protected *;
}
-dontwarn com.chahine.trakt.api.entities.**

-keep public class com.chahine.tmdb.api.entities.** {
  public protected *;
}
-dontwarn com.chahine.tmdb.api.entities.**

-dontwarn android.arch.lifecycle.FullLifecycleObserver

# Keep custom exceptions
-keepnames public class * extends java.lang.Exception
