package com.chahine.showhive.home

import androidx.recyclerview.widget.DiffUtil
import com.chahine.showhive.base.rv.RvDiffUtil
import com.chahine.showhive.home.calendar.CalendarAdapter
import com.chahine.showhive.home.calendar.CalendarEmptyItemView
import com.chahine.showhive.home.calendar.DateHeaderItemView
import com.chahine.showhive.home.calendar.EpisodeItemView
import com.chahine.showhive.home.discover.DiscoverAdapter
import com.chahine.trakt.entities.TrendingShow
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
    fun provideDiscoverAdapter(): DiscoverAdapter {
        return DiscoverAdapter(
            object : DiffUtil.ItemCallback<TrendingShow>() {
                override fun areItemsTheSame(oldItem: TrendingShow, newItem: TrendingShow): Boolean {
                    return oldItem.show.ids.trakt == newItem.show.ids.trakt
                }

                override fun areContentsTheSame(oldItem: TrendingShow, newItem: TrendingShow): Boolean {
                    return oldItem.equals(newItem)
                }
            }
        )
    }
}
