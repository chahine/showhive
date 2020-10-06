package com.chahinem.showhive.home.calendar

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chahinem.showhive.home.R
import com.chahinem.showhive.home.R.string
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter.ofPattern
import javax.inject.Inject

class DateHeaderItemView {

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

        private val header = itemView.findViewById<TextView>(R.id.header)

        fun bind(item: Item) {
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

    companion object {
        private val DATE_FORMATTER = ofPattern("EEEE, MMMM d")
        private val DAY_FORMATTER = ofPattern("d")
    }
}
