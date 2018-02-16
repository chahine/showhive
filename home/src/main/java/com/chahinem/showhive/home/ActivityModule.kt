package com.chahinem.showhive.home

import android.arch.lifecycle.ViewModel
import com.chahinem.showhive.di.ViewModelKey
import com.chahinem.showhive.home.calendar.CalendarAdapter
import com.chahinem.showhive.home.calendar.CalendarViewModel
import com.chahinem.showhive.home.calendar.EpisodeItemView
import com.chahinem.showhive.home.discover.DiscoverViewModel
import com.chahinem.showhive.home.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

@Module abstract class ActivityModule {

  @Binds
  @IntoMap
  @ViewModelKey(CalendarViewModel::class)
  abstract fun bindCalendarViewModel(vm: CalendarViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(DiscoverViewModel::class)
  abstract fun bindDiscoverViewModel(vm: DiscoverViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(ProfileViewModel::class)
  abstract fun bindProfileViewModel(vm: ProfileViewModel): ViewModel

  @Binds
  @IntoMap
  @IntKey(CalendarAdapter.EPISODE)
  abstract fun bindCalendarEpisode(delegate: EpisodeItemView.Delegate): CalendarAdapter.Delegate
}
