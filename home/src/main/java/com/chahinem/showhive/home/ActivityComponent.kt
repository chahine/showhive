package com.chahinem.showhive.home

import android.app.Activity
import com.chahinem.showhive.di.ShowHiveComponent
import com.chahinem.showhive.home.calendar.CalendarFragment
import com.chahinem.showhive.home.discover.DiscoverFragment
import com.chahinem.showhive.home.profile.ProfileFragment
import com.chahinem.showhive.qualifiers.PerActivity
import dagger.BindsInstance
import dagger.Component

@PerActivity
@Component(
    modules = [ActivityModule::class],
    dependencies = [ShowHiveComponent::class]
)
interface ActivityComponent {

  fun inject(activity: HomeActivity)
  fun inject(fragment: CalendarFragment)
  fun inject(fragment: DiscoverFragment)
  fun inject(fragment: ProfileFragment)

  @Component.Builder interface Builder {
    @BindsInstance fun activity(activity: Activity): Builder
    fun appComponent(appComponent: ShowHiveComponent): Builder
    fun build(): ActivityComponent
  }
}
