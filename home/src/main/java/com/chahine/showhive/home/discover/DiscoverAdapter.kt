package com.chahine.showhive.home.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chahine.showhive.home.R
import com.chahine.showhive.home.databinding.ItemImageLineThreeBinding
import com.chahine.trakt.entities.TrendingShow
import timber.log.Timber
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class DiscoverAdapter(
    diffCallback: DiffUtil.ItemCallback<TrendingShow>
) : PagingDataAdapter<TrendingShow, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        private const val SEPARATOR = " • "
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_image_line_three, parent, false)
        ) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(ItemImageLineThreeBinding.bind(holder.itemView)) {
            val item = getItem(position)!!
            val show = item.show
            val airs = show.airs

            val time = if (!airs.time.isNullOrBlank() && !airs.timezone.isNullOrBlank()) {
                try {
                    val dateTime = LocalTime
                        .parse(airs.time, DateTimeFormatter.ofPattern("HH:mm[:ss]"))
                        .atDate(LocalDate.now())
                    ZonedDateTime
                        .ofLocal(dateTime, ZoneId.of(airs.timezone), null)
                        .format(DateTimeFormatter.ofPattern("hh:mm a"))
                } catch (exception: DateTimeParseException) {
                    Timber.log(1, exception)
                }
            } else {
                null
            }

            line1.text = listOfNotNull(show.title).joinToString(SEPARATOR)
            line2.text = listOfNotNull(show.overview).joinToString(SEPARATOR)
            line3.text = listOfNotNull(show.network, show.certification, time).joinToString(SEPARATOR)
        }
    }
}
