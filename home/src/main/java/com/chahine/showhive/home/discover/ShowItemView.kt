package com.chahine.showhive.home.discover

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chahine.showhive.home.databinding.ItemImageLineThreeBinding
import com.chahine.showhive.home.discover.DiscoverInteractor.Companion.SEPARATOR
import com.chahine.trakt.entities.TrendingShow
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ShowItemView {

//    class Delegate @Inject constructor() : DiscoverAdapter.Delegate {
//
//        override fun layoutId() = R.layout.item_image_line_three
//
//        override fun create(parent: ViewGroup) = Holder(itemView(parent))
//
//        override fun bind(item: DiscoverAdapter.Item, holder: RecyclerView.ViewHolder) {
//            if (holder is Holder && item is Item) {
//                holder.bind(item)
//            }
//        }
//    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemImageLineThreeBinding.bind(itemView)

        fun bind(item: TrendingShow) = with(binding) {
            val show = item.show
            val airs = show.airs

            val time = if (!airs.time.isNullOrBlank() && !airs.timezone.isNullOrBlank()) {
                val dateTime = LocalTime
                    .parse(airs.time, DateTimeFormatter.ofPattern("HH:mm"))
                    .atDate(LocalDate.now())
                ZonedDateTime
                    .ofLocal(dateTime, ZoneId.of(airs.timezone), null)
                    .format(DateTimeFormatter.ofPattern("hh:mm a"))
            } else {
                null
            }

            line1.text = listOfNotNull(show.title).joinToString(SEPARATOR)
            line2.text = listOfNotNull(show.overview).joinToString(SEPARATOR)
            line3.text = listOfNotNull(show.network, show.certification, time).joinToString(SEPARATOR)
        }
    }

    data class Item(val line1: CharSequence, val line2: CharSequence, val line3: CharSequence) : DiscoverAdapter.Item {
        override fun itemViewType() = DiscoverAdapter.SHOW
    }
}
