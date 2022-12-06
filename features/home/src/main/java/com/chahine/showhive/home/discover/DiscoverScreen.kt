package com.chahine.showhive.home.discover

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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.chahine.showhive.base.theme.ShowHiveTheme
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

private const val SEPARATOR = " • "

@Composable
fun DiscoverScreen(shows: Flow<PagingData<DiscoverUiModel>>) {
    val showItems = shows.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(items = showItems, key = { it.show.ids.trakt }) { model: DiscoverUiModel? ->
            model ?: return@items
            val show = model.show
            val airs = show.airs

            val time = if (!airs.time.isNullOrBlank() && !airs.timezone.isNullOrBlank()) {
                try {
                    val dateTime =
                        LocalTime
                            .parse(
                                airs.time,
                                DateTimeFormatter.ofPattern("HH:mm[:ss]")
                            )
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

            val line1Text = listOfNotNull(show.title).joinToString(SEPARATOR)
            val line2Text = listOfNotNull(show.overview).joinToString(SEPARATOR)
            val line3Text = listOfNotNull(show.network, show.certification, time).joinToString(SEPARATOR)

            CalendarShowItem(model.posterUrl, arrayOf(line1Text, line2Text, line3Text))
        }
    }
}

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

@Preview
@Composable
fun CalendarShowItemPreview() {
    ShowHiveTheme {
        CalendarShowItem(null, arrayOf("line1", "line2", "line3"))
    }
}
