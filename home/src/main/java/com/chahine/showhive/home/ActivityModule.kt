package com.chahine.showhive.home

import com.chahine.showhive.base.rv.RvDiffUtil
import com.chahine.showhive.home.calendar.CalendarAdapter
import com.chahine.showhive.home.calendar.CalendarEmptyItemView
import com.chahine.showhive.home.calendar.DateHeaderItemView
import com.chahine.showhive.home.calendar.EpisodeItemView
import com.chahine.showhive.home.discover.DiscoverAdapter
import com.chahine.showhive.home.discover.ShowItemView
import dagger.Module
import dagger.Provides

@Module(includes = [ActivityBindingModule::class])
class ActivityModule {

    @Provides
    fun provideCalendarAdapter(diffUtil: RvDiffUtil<CalendarAdapter.Item>): CalendarAdapter {
        return CalendarAdapter(
            diffUtil,
            mapOf(
                CalendarAdapter.EMPTY to CalendarEmptyItemView.Delegate(),
                CalendarAdapter.DATE_HEADER to DateHeaderItemView.Delegate(),
                CalendarAdapter.EPISODE to EpisodeItemView.Delegate()
            )
        )
    }

    @Provides
    fun provideDiscoverAdapter(diffUtil: RvDiffUtil<DiscoverAdapter.Item>): DiscoverAdapter {
        return DiscoverAdapter(
            diffUtil,
            mapOf(
                DiscoverAdapter.SHOW to ShowItemView.Delegate()
            )
        )
    }
}
