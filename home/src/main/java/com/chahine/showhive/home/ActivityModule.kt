package com.chahine.showhive.home

import com.chahine.showhive.home.calendar.CalendarAdapter
import com.chahine.showhive.home.calendar.CalendarDiffUtil
import com.chahine.showhive.home.calendar.CalendarEmptyItemView
import com.chahine.showhive.home.calendar.DateHeaderItemView
import com.chahine.showhive.home.calendar.EpisodeItemView
import dagger.Module
import dagger.Provides

@Module(includes = [ActivityBindingModule::class])
class ActivityModule {

    @Provides
    fun provideAdapter(diffUtil: CalendarDiffUtil): CalendarAdapter {
        return CalendarAdapter(
            diffUtil,
            mapOf(
                CalendarAdapter.EMPTY to CalendarEmptyItemView.Delegate(),
                CalendarAdapter.DATE_HEADER to DateHeaderItemView.Delegate(),
                CalendarAdapter.EPISODE to EpisodeItemView.Delegate()
            )
        )
    }
}
