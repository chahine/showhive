package com.chahinem.showhive.home

import android.app.Activity
import com.chahinem.showhive.di.ShowHiveComponent
import com.chahinem.showhive.qualifiers.PerActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@PerActivity
@Component(
    modules = [ActivityModule::class, AndroidInjectionModule::class],
    dependencies = [ShowHiveComponent::class]
)
interface ActivityComponent : AndroidInjector<HomeActivity> {

  //  fun inject(activity: HomeActivity)
  //  fun inject(fragment: CalendarFragment)
//  fun inject(fragment: DiscoverFragment)
//  fun inject(fragment: ProfileFragment)
//
  @Component.Builder interface Builder {
    @BindsInstance fun activity(activity: Activity): Builder
    fun appComponent(appComponent: ShowHiveComponent): Builder
    fun build(): ActivityComponent
  }
}
