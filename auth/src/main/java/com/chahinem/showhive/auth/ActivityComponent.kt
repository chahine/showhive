package com.chahinem.showhive.auth

import android.app.Activity
import com.chahinem.showhive.di.ShowHiveComponent
import com.chahinem.showhive.qualifiers.PerActivity
import dagger.BindsInstance
import dagger.Component

@PerActivity
@Component(
    modules = [ActivityModule::class],
    dependencies = [ShowHiveComponent::class]
)
interface ActivityComponent {

  fun inject(activity: SplashActivity)

  @Component.Builder
  interface Builder {
    @BindsInstance fun activity(activity: Activity): Builder
    fun activityModule(module: ActivityModule): Builder
    fun appComponent(appComponent: ShowHiveComponent): Builder
    fun build(): ActivityComponent
  }
}
