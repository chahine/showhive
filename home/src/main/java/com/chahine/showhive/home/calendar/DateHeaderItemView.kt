package com.chahine.showhive.home.calendar

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chahine.showhive.home.R
import com.chahine.showhive.home.R.string
import com.chahine.showhive.home.databinding.ItemCalendarDateHeaderBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ofPattern
import javax.inject.Inject

class DateHeaderItemView {

    companion object {
        private val DATE_FORMATTER = ofPattern("EEEE, MMMM d")
        private val DAY_FORMATTER = ofPattern("d")
    }

    class Delegate @Inject constructor() : CalendarAdapter.Delegate {

        override fun layoutId() = R.layout.item_calendar_date_header

        override fun create(parent: ViewGroup) = Holder(itemView(parent))

        override fun bind(item: CalendarAdapter.Item, holder: ViewHolder) {
            if (holder is Holder && item is Item) {
                holder.bind(item)
            }
        }
    }

    class Holder(itemView: View) : ViewHolder(itemView) {

        private val binding = ItemCalendarDateHeaderBinding.bind(itemView)

        fun bind(item: Item) = with(binding) {
            val now = LocalDate.now()
            val localDate = item.dateTime

            header.text = when (localDate) {
                now -> itemView.context.getString(string.today)
                now.plusDays(1) -> itemView.context.getString(string.tomorrow)
                now.minusDays(1) -> itemView.context.getString(string.yesterday)
                else -> localDate.format(DATE_FORMATTER) +
                        getDayOfMonthSuffix(localDate.format(DAY_FORMATTER).toInt())
            }
        }

        @Suppress("MagicNumber")
        private fun getDayOfMonthSuffix(n: Int): String {
            if (n in 11..13) {
                return "th"
            }
            return when (n % 10) {
                1 -> "st"
                2 -> "nd"
                3 -> "rd"
                else -> "th"
            }
        }
    }

    class Item(val dateTime: LocalDate) : CalendarAdapter.Item {
        override fun itemViewType() = CalendarAdapter.DATE_HEADER
    }
}
