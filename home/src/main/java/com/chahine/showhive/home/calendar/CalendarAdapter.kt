package com.chahine.showhive.home.calendar

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chahine.showhive.base.rv.RvAdapter
import com.chahine.showhive.base.rv.RvDelegate
import com.chahine.showhive.base.rv.RvItem
import com.chahine.showhive.home.calendar.CalendarAdapter.Item

class CalendarAdapter(delegates: Map<Int, Delegate>) : RvAdapter<Item>(delegates) {

    interface Delegate : RvDelegate<Item, ViewHolder>
    interface Item : RvItem

    companion object {
        const val EPISODE = 1
        const val DATE_HEADER = 2
        const val EMPTY = 3
    }
}
