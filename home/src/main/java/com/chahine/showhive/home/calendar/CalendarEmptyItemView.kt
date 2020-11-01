package com.chahine.showhive.home.calendar

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chahine.showhive.home.R
import javax.inject.Inject

class CalendarEmptyItemView {
    class Delegate @Inject constructor() : CalendarAdapter.Delegate {

        override fun layoutId() = R.layout.item_empty_calendar

        override fun create(parent: ViewGroup) = Holder(itemView(parent))

        override fun bind(item: CalendarAdapter.Item, holder: ViewHolder) {
            // TODO: implement view
        }
    }

    class Holder(itemView: View) : ViewHolder(itemView)

    class Item : CalendarAdapter.Item {
        override fun itemViewType() = CalendarAdapter.EMPTY
    }
}
