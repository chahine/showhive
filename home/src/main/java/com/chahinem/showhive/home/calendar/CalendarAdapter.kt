package com.chahinem.showhive.home.calendar

import android.support.v7.widget.RecyclerView.ViewHolder
import com.chahinem.showhive.base.rv.RvAdapter
import com.chahinem.showhive.base.rv.RvDelegate
import com.chahinem.showhive.base.rv.RvItem
import com.chahinem.showhive.home.calendar.CalendarAdapter.Item

class CalendarAdapter(delegates: Map<Int, CalendarAdapter.Delegate>) : RvAdapter<Item>(delegates) {

  interface Delegate : RvDelegate<Item, ViewHolder>
  interface Item : RvItem

  companion object {
    const val EPISODE = 1
    const val DATE_HEADER = 2
    const val EMPTY = 3
  }
}
