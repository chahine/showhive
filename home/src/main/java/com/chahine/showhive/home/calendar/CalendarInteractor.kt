package com.chahine.showhive.home.calendar

import android.content.res.Resources
import com.chahine.showhive.home.R
import com.chahine.showhive.home.calendar.CalendarModel.CalendarCardSuccess
import com.chahine.showhive.home.calendar.CalendarModel.CalendarFailure
import com.chahine.showhive.home.calendar.CalendarModel.CalendarProgress
import com.chahine.trakt.api.TraktApi
import com.chahine.trakt.entities.CalendarShowEntry
import com.chahine.trakt.entities.Extended
import io.reactivex.rxjava3.core.ObservableTransformer
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.ofPattern
import javax.inject.Inject

class CalendarInteractor @Inject constructor(
    private val resources: Resources,
    private val traktApi: TraktApi,
) {

    companion object {
        // The maximum amount of days you can send is 31
        private const val DAY_WINDOW = 15
        private val DAY_FORMATTER = ofPattern("yyyy-MM-dd")

        private const val SEPARATOR = " • "
    }

    fun calendar(): ObservableTransformer<in CalendarEvent.LoadCalendar, out CalendarModel> {
        return ObservableTransformer { event ->
            event.switchMap {
                val startDate = ZonedDateTime.now().minusDays(DAY_WINDOW.toLong()).format(DAY_FORMATTER)
                traktApi
                    .myShows(startDate, DAY_WINDOW * 2, Extended.FULL)
                    .map { episodes ->
                        if (episodes.isEmpty()) {
                            return@map CalendarEmptyItemView.Item
                        }
                        val items = mutableListOf<CalendarAdapter.Item>()
                        episodes
                            .groupBy { it.firstAired.toLocalDate() }
                            .forEach { (date, episode) ->
                                items += DateHeaderItemView.Item(date)
                                items += episode.map { makeEpisodeItem(it) }
                            }
                        CalendarCardSuccess(items)
                    }
                    .toObservable()
                    .cast(CalendarModel::class.java)
                    .onErrorReturn { CalendarFailure(it) }
                    .startWithItem(CalendarProgress)
            }
        }
    }

    private fun makeEpisodeItem(item: CalendarShowEntry): EpisodeItemView.Item {
        val show = item.show
        val episode = item.episode

        val episodeNumber = resources.getString(R.string.episode_number_format, episode.season, episode.number)

        val line1 = listOfNotNull(episodeNumber, show.title).joinToString(SEPARATOR)
        val line2 = episode.title
        val line3 = listOfNotNull(
            show.network,
            show.certification,
            episode.firstAired
                .withZoneSameInstant(ZoneId.systemDefault())
                .format(ofPattern("hh:mm a"))
        ).joinToString(SEPARATOR)

        return EpisodeItemView.Item(line1, line2, line3)
    }
}
