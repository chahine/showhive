package com.chahinem.showhive.home

import android.app.Activity
import android.arch.lifecycle.ViewModel
import com.chahinem.showhive.di.ViewModelKey
import com.chahinem.showhive.home.calendar.CalendarViewModel
import com.chahinem.showhive.home.discover.DiscoverViewModel
import com.chahinem.showhive.home.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
interface ActivityBindingModule {

  //  @ContributesAndroidInjector
//  fun contributeHomeInjector(): HomeActivity
  @Binds
  @IntoMap
  @ActivityKey(HomeActivity::class)
  fun bindHomeInjectorFactory(builder: ActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>

  @Binds @IntoMap @ViewModelKey(CalendarViewModel::class)
  fun bindCalendarViewModel(vm: CalendarViewModel): ViewModel

  @Binds @IntoMap @ViewModelKey(DiscoverViewModel::class)
  fun bindDiscoverViewModel(vm: DiscoverViewModel): ViewModel

  @Binds @IntoMap @ViewModelKey(ProfileViewModel::class)
  fun bindProfileViewModel(vm: ProfileViewModel): ViewModel
}
