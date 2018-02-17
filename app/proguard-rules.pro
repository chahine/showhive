-dontwarn java.lang.invoke.*

-keepattributes *Annotation*
-keep class javax.inject.** { *; }

# OkHttp
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.OpenSSLProvider

# Retrofit 2.X
## https://square.github.io/retrofit/ ##
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8
-dontwarn okio.**
-dontwarn javax.annotation.**
-keepattributes Signature
-keepattributes Exceptions

# GSON
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }

# RxJava
-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

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

-keep public class com.chahinem.trakt.entities.** {
  public protected *;
}
-dontwarn com.chahinem.trakt.entities.**

-keep public class com.chahinem.tmdb.entities.** {
  public protected *;
}
-dontwarn com.chahinem.tmdb.entities.**

-dontwarn android.arch.lifecycle.FullLifecycleObserver

# Keep the names of the model and event classes
-keepnames public class * extends **Event
-keepnames public class * extends **Model

# Keep custom exceptions
-keepnames public class * extends java.lang.Exception
