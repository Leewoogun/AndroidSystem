package com.lwg.ui.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.theme.Blue
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.Red

@Composable
fun DayOfWeekComponent(
    verticalPadding: Dp = 10.dp
) {
    val dayOfWeekList: List<DayOfWeek> = listOf(
        DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = verticalPadding),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items(dayOfWeekList) { dayOfWeek ->
            DayOfWeekText(
                dayOfWeek
            )
        }
    }
}

@Composable
private fun DayOfWeekText(
    dayOfWeek: DayOfWeek
) {
    val color = when (dayOfWeek) {
        DayOfWeek.SATURDAY -> { Blue }
        DayOfWeek.SUNDAY -> { Red }
        else -> { MaterialTheme.colorScheme.onBackground }
    }

    Text(
        modifier = Modifier
            .padding(horizontal = 10.dp),
        text = dayOfWeek.title,
        color = color
    )
}

@Composable
@Preview(showBackground = true)
private fun DayOfWeekComponentPreview() {
    LwgTheme {
        DayOfWeekComponent()
    }
}