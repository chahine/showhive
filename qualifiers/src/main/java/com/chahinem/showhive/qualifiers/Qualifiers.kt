package com.chahinem.showhive.qualifiers

import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class CacheSize

@Qualifier
@Retention(RUNTIME)
annotation class Trakt

@Scope
@Retention(RUNTIME)
annotation class PerApp

@Scope
@Retention(RUNTIME)
annotation class PerActivity
