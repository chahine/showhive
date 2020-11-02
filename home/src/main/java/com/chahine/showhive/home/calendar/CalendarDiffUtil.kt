package com.chahine.showhive.home.calendar

import androidx.recyclerview.widget.DiffUtil
import javax.inject.Inject

class CalendarDiffUtil @Inject constructor() : DiffUtil.ItemCallback<CalendarAdapter.Item>() {
    override fun areItemsTheSame(oldItem: CalendarAdapter.Item, newItem: CalendarAdapter.Item): Boolean {
        return oldItem.itemViewType() == newItem.itemViewType()
    }

    override fun areContentsTheSame(oldItem: CalendarAdapter.Item, newItem: CalendarAdapter.Item): Boolean {
        return oldItem.equals(newItem)
    }
}
