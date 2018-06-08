package com.chahinem.showhive.home

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface ActivitySubcomponent : AndroidInjector<HomeActivity> {

  @Subcomponent.Builder
  abstract class Builder : AndroidInjector.Builder<HomeActivity>()
}