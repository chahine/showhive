package com.chahine.showhive.home

import android.app.Activity
import com.chahine.showhive.di.ShowHiveComponent
import com.chahine.showhive.home.calendar.CalendarFragment
import com.chahine.showhive.home.discover.DiscoverFragment
import com.chahine.showhive.home.profile.ProfileFragment
import com.chahine.showhive.qualifiers.ActivityScope
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(
    modules = [ActivityModule::class],
    dependencies = [ShowHiveComponent::class]
)
interface ActivityComponent {

    fun inject(activity: HomeActivity)
    fun inject(fragment: CalendarFragment)
    fun inject(fragment: DiscoverFragment)
    fun inject(fragment: ProfileFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: Activity): Builder
        fun appComponent(appComponent: ShowHiveComponent): Builder
        fun build(): ActivityComponent
    }
}
