package com.chahine.showhive.home.calendar

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chahine.showhive.home.R
import com.chahine.showhive.home.databinding.ItemCalendarEpisodeBinding
import com.chahine.trakt.entities.CalendarShowEntry
import java.time.format.DateTimeFormatter.ofPattern
import javax.inject.Inject

class EpisodeItemView {

    companion object {
        private const val SEPARATOR = " • "
    }

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
            line1.text = item.entry.show.title + SEPARATOR + itemView.resources.getString(
                R.string.episode_number_format,
                item.entry.episode.season,
                item.entry.episode.number
            )
            line2.text = item.entry.episode.title
            line3.text = item.entry.show.network + SEPARATOR +
                    item.entry.show.certification + SEPARATOR +
                    item.entry.show.firstAired!!.toLocalTime().format(ofPattern("HH:mm aa"))
        }
    }

    data class Item(val entry: CalendarShowEntry) : CalendarAdapter.Item {
        override fun itemViewType() = CalendarAdapter.EPISODE
    }
}
