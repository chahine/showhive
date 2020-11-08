package com.chahine.showhive.home.calendar

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chahine.showhive.home.R
import com.chahine.showhive.home.databinding.ItemCalendarEpisodeBinding
import javax.inject.Inject

class EpisodeItemView {

    class Delegate @Inject constructor() : CalendarAdapter.Delegate {

        override fun layoutId() = R.layout.item_calendar_episode

        override fun create(parent: ViewGroup) = Holder(itemView(parent))

        override fun bind(item: CalendarAdapter.Item, holder: ViewHolder) {
            if (holder is Holder && item is Item) {
                holder.bind(item)
            }
        }
    }

    class Holder(itemView: View) : ViewHolder(itemView) {

        private val binding = ItemCalendarEpisodeBinding.bind(itemView)

        fun bind(item: Item) = with(binding) {
            line1.text = item.line1
            line2.text = item.line2
            line3.text = item.line3
        }
    }

    data class Item(val line1: CharSequence, val line2: CharSequence, val line3: CharSequence) : CalendarAdapter.Item {
        override fun itemViewType() = CalendarAdapter.EPISODE
    }
}
