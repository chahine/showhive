package com.chahine.showhive.auth

import android.app.Activity
import com.chahine.showhive.di.ShowHiveComponent
import com.chahine.showhive.qualifiers.ActivityScope
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(
    modules = [ActivityModule::class],
    dependencies = [ShowHiveComponent::class]
)
interface ActivityComponent {

    fun inject(activity: SplashActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: Activity): Builder
        fun activityModule(module: ActivityModule): Builder
        fun appComponent(appComponent: ShowHiveComponent): Builder
        fun build(): ActivityComponent
    }
}
