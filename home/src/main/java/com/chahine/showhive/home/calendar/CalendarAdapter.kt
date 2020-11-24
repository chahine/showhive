package com.chahine.showhive.home.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.chahine.showhive.home.R
import com.chahine.showhive.home.databinding.ItemCalendarDateHeaderBinding
import com.chahine.showhive.home.databinding.ItemImageLineThreeBinding
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter.ofPattern

class CalendarAdapter(diffCallback: DiffUtil.ItemCallback<CalendarUiModel>) :
    PagingDataAdapter<CalendarUiModel, ViewHolder>(diffCallback) {

    companion object {
        private const val SEPARATOR = " • "
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            R.layout.item_image_line_three -> EpisodeViewHolder.create(parent)
            else -> HeaderViewHolder.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CalendarUiModel.Episode -> R.layout.item_image_line_three
            is CalendarUiModel.Header -> R.layout.item_calendar_date_header
            null -> throw UnsupportedOperationException("Unknown view")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { uiModel ->
            when (uiModel) {
                is CalendarUiModel.Episode -> (holder as EpisodeViewHolder).bind(uiModel)
                is CalendarUiModel.Header -> (holder as HeaderViewHolder).bind(uiModel.date)
            }
        }
    }

    class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            fun create(parent: ViewGroup): EpisodeViewHolder = EpisodeViewHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_image_line_three, parent, false)
            )
        }

        private val binding = ItemImageLineThreeBinding.bind(view)

        fun bind(item: CalendarUiModel.Episode) = with(binding) {
            val show = item.entry.show
            val episode = item.entry.episode
            val resources = itemView.resources

            val episodeNumber = resources.getString(R.string.episode_number_format, episode.season, episode.number)

            line1.text = listOfNotNull(episodeNumber, show.title).joinToString(SEPARATOR)
            line2.text = episode.title
            line3.text = listOfNotNull(
                show.network,
                show.certification,
                episode.firstAired
                    .withZoneSameInstant(ZoneId.systemDefault())
                    .format(ofPattern("MMM d hh:mm a"))
            ).joinToString(SEPARATOR)

            Glide.with(binding.poster.context)
                .load(item.posterUrl)
                .centerCrop()
                .placeholder(R.color.colorSecondary)
                .into(poster)
        }
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {

            private val DATE_FORMATTER = ofPattern("EEEE, MMMM d")
            private val DAY_FORMATTER = ofPattern("d")

            fun create(parent: ViewGroup): HeaderViewHolder = HeaderViewHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_calendar_date_header, parent, false)
            )
        }

        private val binding = ItemCalendarDateHeaderBinding.bind(itemView)

        fun bind(localDate: LocalDate) = with(binding) {
            val today = LocalDate.now()

            header.text = when (localDate) {
                today -> itemView.context.getString(R.string.today)
                today.plusDays(1) -> itemView.context.getString(R.string.tomorrow)
                today.minusDays(1) -> itemView.context.getString(R.string.yesterday)
                else -> localDate.format(DATE_FORMATTER) + getDayOfMonthSuffix(localDate.format(DAY_FORMATTER).toInt())
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
}
