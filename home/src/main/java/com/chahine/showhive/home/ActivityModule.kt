package com.chahine.showhive.home

import androidx.recyclerview.widget.DiffUtil
import com.chahine.showhive.home.calendar.CalendarAdapter
import com.chahine.showhive.home.discover.DiscoverAdapter
import com.chahine.showhive.home.discover.DiscoverUiModel
import dagger.Module
import dagger.Provides

@Module(includes = [ActivityBindingModule::class])
class ActivityModule {

    @Provides
    fun provideCalendarAdapter(): CalendarAdapter {
        return CalendarAdapter()
    }

    @Provides
    fun provideDiscoverAdapter(): DiscoverAdapter {
        return DiscoverAdapter(
            object : DiffUtil.ItemCallback<DiscoverUiModel>() {
                override fun areItemsTheSame(oldItem: DiscoverUiModel, newItem: DiscoverUiModel): Boolean {
                    return oldItem.show.ids.trakt == newItem.show.ids.trakt
                }

                override fun areContentsTheSame(oldItem: DiscoverUiModel, newItem: DiscoverUiModel): Boolean {
                    return oldItem == newItem
                }
            }
        )
    }
}
