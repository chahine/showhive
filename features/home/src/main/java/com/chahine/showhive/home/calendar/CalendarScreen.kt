package com.chahine.showhive.home.calendar

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.Size
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.chahine.showhive.base.theme.ShowHiveTheme
import com.chahine.showhive.home.R
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val DATE_FORMATTER = DateTimeFormatter.ofPattern("EEEE, MMMM d")
private val DAY_FORMATTER = DateTimeFormatter.ofPattern("d")
private val AIRTIME_FORMATTER = DateTimeFormatter.ofPattern("MMM d hh:mm a")

@Composable
fun CalendarScreen(episodes: Flow<PagingData<CalendarUiModel>>) {
    val calendarItems = episodes.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(items = calendarItems, key = { it.hashCode() }) { model: CalendarUiModel? ->
            model ?: return@items
            when (model) {
                is CalendarUiModel.Episode -> {
                    val show = model.entry.show
                    val episode = model.entry.episode
                    val episodeNumber = stringResource(
                        R.string.episode_number_format, episode.season ?: 0, episode.number ?: 0
                    )

                    val line1Text = listOfNotNull(episodeNumber, show.title).joinToString(SEPARATOR)
                    val line2Text = episode.title ?: ""
                    val line3Text = listOfNotNull(
                        show.network,
                        show.certification,
                        episode.firstAired.withZoneSameInstant(ZoneId.systemDefault()).format(AIRTIME_FORMATTER)
                    ).joinToString(SEPARATOR)

                    CalendarShowItem(model.posterUrl, arrayOf(line1Text, line2Text, line3Text))
                }

                is CalendarUiModel.Header -> {
                    val today = LocalDate.now()

                    val text = when (model.date) {
                        today -> stringResource(R.string.today)
                        today.plusDays(1) -> stringResource(R.string.tomorrow)
                        today.minusDays(1) -> stringResource(R.string.yesterday)
                        else -> {
                            model.date.format(DATE_FORMATTER) + getDayOfMonthSuffix(
                                model.date.format(DAY_FORMATTER).toInt()
                            )
                        }
                    }

                    CalendarDateHeader(text)
                }
            }
        }
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

private const val SEPARATOR = " • "

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun CalendarShowItem(url: String?, @Size(3) lines: Array<String>) {
    val (line1Text, line2Text, line3Text) = lines

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        GlideImage(
            model = url,
            contentDescription = null,
            modifier = Modifier
                .width(80.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(2.dp)),
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .height(120.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                text = AnnotatedString(line1Text),
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
            )
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = AnnotatedString(line2Text),
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
            )
            Text(
                text = AnnotatedString(line3Text),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
            )
        }
    }
}

@Composable
fun CalendarDateHeader(text: String) {
    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = text,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.primary,
    )
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CalendarDateHeaderPrev() {
    ShowHiveTheme {
        CalendarDateHeader("Saturday, September 3rd")
    }
}

@Preview
@Composable
fun CalendarShowItemPreview() {
    ShowHiveTheme {
        CalendarShowItem("model", arrayOf("line1", "line2", "line3"))
    }
}
