package com.chahine.showhive.home

import androidx.recyclerview.widget.DiffUtil
import com.chahine.showhive.home.calendar.CalendarAdapter
import com.chahine.showhive.home.calendar.CalendarUiModel
import com.chahine.showhive.home.discover.DiscoverAdapter
import com.chahine.showhive.home.discover.DiscoverUiModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    fun provideCalendarAdapter(): CalendarAdapter {
        return CalendarAdapter(object : DiffUtil.ItemCallback<CalendarUiModel>() {
            override fun areItemsTheSame(oldItem: CalendarUiModel, newItem: CalendarUiModel): Boolean {
                return oldItem is CalendarUiModel.Episode &&
                    newItem is CalendarUiModel.Episode &&
                    oldItem.entry == newItem.entry ||
                    oldItem is CalendarUiModel.Header &&
                    newItem is CalendarUiModel.Header &&
                    oldItem.date == newItem.date
            }

            override fun areContentsTheSame(oldItem: CalendarUiModel, newItem: CalendarUiModel): Boolean =
                oldItem == newItem
        })
    }

    @Provides
    fun provideDiscoverAdapter(): DiscoverAdapter {
        return DiscoverAdapter(object : DiffUtil.ItemCallback<DiscoverUiModel>() {
            override fun areItemsTheSame(oldItem: DiscoverUiModel, newItem: DiscoverUiModel): Boolean {
                return oldItem.show.ids.trakt == newItem.show.ids.trakt
            }

            override fun areContentsTheSame(oldItem: DiscoverUiModel, newItem: DiscoverUiModel): Boolean {
                return oldItem == newItem
            }
        })
    }
}
