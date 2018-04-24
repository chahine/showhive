package com.chahinem.showhive.home

import com.chahinem.showhive.home.calendar.CalendarAdapter
import com.chahinem.showhive.home.calendar.CalendarEmptyItemView
import com.chahinem.showhive.home.calendar.DateHeaderItemView
import com.chahinem.showhive.home.calendar.EpisodeItemView
import dagger.Module
import dagger.Provides

@Module(includes = [ActivityBindingModule::class])
class ActivityModule {

  @Provides fun provideAdapter(): CalendarAdapter {
    return CalendarAdapter(mapOf(
        CalendarAdapter.EMPTY to CalendarEmptyItemView.Delegate(),
        CalendarAdapter.DATE_HEADER to DateHeaderItemView.Delegate(),
        CalendarAdapter.EPISODE to EpisodeItemView.Delegate()
    ))
  }
}
