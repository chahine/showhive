package com.chahinem.showhive.auth

import com.chahinem.showhive.di.ShowHiveComponent
import com.chahinem.showhive.qualifiers.PerActivity
import dagger.Component

@PerActivity
@Component(
    modules = [ActivityModule::class],
    dependencies = [ShowHiveComponent::class]
)
interface ActivityComponent {

  fun inject(activity: SplashActivity)
//  fun inject(viewModel: SplashViewModel)

  @Component.Builder
  interface Builder {
    fun appComponent(appComponent: ShowHiveComponent): Builder
    fun activityModule(module: ActivityModule): Builder
    fun build(): ActivityComponent
  }
}
