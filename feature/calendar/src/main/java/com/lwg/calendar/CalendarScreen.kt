package com.lwg.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lwg.calendar.component.MovieCalendar
import com.lwg.designsystem.component.LwgHorizontalDivider
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.model.movie.Movie
import com.lwg.ui.EmptyScreen
import com.lwg.ui.calendar.CalendarComponent
import com.lwg.util.Logger
import java.time.LocalDate

@Composable
internal fun CalendarScreen(
    calendarState: Calendar,
    favoriteMovie: List<Movie>,
    onChangeSelectedDay: (LocalDate) -> Unit
) {
    Logger.i("favoriteMovie: $favoriteMovie")
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CalendarComponent(
            year = calendarState.year,
            month = calendarState.month,
            selectedDay = calendarState.selectedDay,
            onChangeSelectedDay = onChangeSelectedDay
        )
        LwgHorizontalDivider(color = MaterialTheme.colorScheme.primary)

        if (favoriteMovie.isEmpty()) {
            EmptyScreen(
                title = stringResource(R.string.no_movie)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(vertical = 10.dp)
            ) {
                items(favoriteMovie) { movie ->
                    MovieCalendar(movie)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun CalendarScreenPreview() {
    LwgTheme {
        CalendarScreen(
            Calendar(2025, 3),
            favoriteMovie = emptyList(),
            onChangeSelectedDay = {}
        )
    }
}