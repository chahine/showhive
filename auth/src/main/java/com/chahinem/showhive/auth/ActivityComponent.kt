package com.chahinem.showhive.auth

import android.support.v7.app.AppCompatActivity
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
    @BindsInstance fun activity(activity: AppCompatActivity): Builder

    fun activityModule(module: ActivityModule): Builder
    fun appComponent(appComponent: ShowHiveComponent): Builder

    fun build(): ActivityComponent
  }
}
