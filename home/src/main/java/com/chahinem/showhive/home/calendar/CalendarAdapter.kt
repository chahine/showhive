package com.chahinem.showhive.home.calendar

import android.support.v7.widget.RecyclerView.ViewHolder
import com.chahinem.showhive.base.rv.RvAdapter
import com.chahinem.showhive.base.rv.RvDelegate
import com.chahinem.showhive.base.rv.RvItem
import com.chahinem.showhive.home.calendar.CalendarAdapter.Item
import javax.inject.Inject

class CalendarAdapter @Inject constructor() : RvAdapter<Item>() {

  init {
    delegates[EPISODE] = EpisodeItemView.Delegate()
  }

  interface Delegate : RvDelegate<Item, ViewHolder>
  interface Item : RvItem

  companion object {
    const val EPISODE = 1
  }
}