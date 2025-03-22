package com.lwg.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.component.LwgVerticalSpacer
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.LwgTypo
import com.lwg.designsystem.theme.Red
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarComponent(
    year: Int,
    month: Int,
    selectedDay: LocalDate,
    onChangeSelectedDay: (LocalDate) -> Unit,
) {
    val dayWithWeekDay = getDaysWithWeekday(year, month) // 날짜 + 요일 정보
    Column {
        DayOfWeekComponent()

        Column {
            dayWithWeekDay.forEach { week ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    week.forEach { (day, weekday) ->
                        val isSelected = checkSelectedDay(year, month, day, selectedDay)
                        val textColor = when {
                            isSelected -> MaterialTheme.colorScheme.onPrimary
                            weekday == DayOfWeek.SUNDAY -> Red
                            else -> MaterialTheme.colorScheme.onBackground
                        }

                        val dayText = if (day == 0) "" else day.toString()

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .clickable {
                                    if (day != 0) {
                                        val date = LocalDate.of(year, month, day)
                                        onChangeSelectedDay(date)
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Column {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = dayText,
                                        style = LwgTypo.typography.bodyLargeR,
                                        color = textColor
                                    )
                                }
                                LwgVerticalSpacer(10.dp)
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun getDaysWithWeekday(year: Int, month: Int): List<List<Pair<Int, DayOfWeek>>> {
    val daysInMonth = YearMonth.of(year, month).lengthOfMonth()
    val firstDayOfWeek = LocalDate.of(year, month, 1).dayOfWeek
    val lastDayOfWeek = LocalDate.of(year, month, daysInMonth).dayOfWeek

    val days = mutableListOf<Pair<Int, DayOfWeek>>()

    // 첫 주의 빈 칸 추가 (첫째 날이 월요일이 아니면 앞에 빈 칸 추가)
    repeat(firstDayOfWeek.value % 7) { days.add(0 to DayOfWeek.MONDAY) }

    // 날짜 추가
    (1..daysInMonth).forEach { day ->
        val date = LocalDate.of(year, month, day)
        val mappedDayOfWeek = mapToCustomDayOfWeek(date.dayOfWeek)
        days.add(day to mappedDayOfWeek)
    }

    // 마지막 주의 빈 칸 추가 (마지막 날이 일요일이 아니면 뒤에 빈 칸 추가)
    repeat(6 - (lastDayOfWeek.value % 7)) { days.add(0 to DayOfWeek.MONDAY) }

    // 7개씩 나누어 반환 (주 단위)
    return days.chunked(7)
}

private fun mapToCustomDayOfWeek(dayOfWeek: java.time.DayOfWeek): DayOfWeek {
    return when (dayOfWeek) {
        java.time.DayOfWeek.MONDAY -> DayOfWeek.MONDAY
        java.time.DayOfWeek.TUESDAY -> DayOfWeek.TUESDAY
        java.time.DayOfWeek.WEDNESDAY -> DayOfWeek.WEDNESDAY
        java.time.DayOfWeek.THURSDAY -> DayOfWeek.THURSDAY
        java.time.DayOfWeek.FRIDAY -> DayOfWeek.FRIDAY
        java.time.DayOfWeek.SATURDAY -> DayOfWeek.SATURDAY
        java.time.DayOfWeek.SUNDAY -> DayOfWeek.SUNDAY
    }
}

private fun checkSelectedDay(year: Int, month: Int, day: Int, selectedDay: LocalDate): Boolean {
    if (day == 0) return false
    val date = LocalDate.of(year, month, day)

    return date == selectedDay
}

@Composable
@Preview(showBackground = true)
private fun CalendarComponentPreview() {
    LwgTheme {
        val currentDate = LocalDate.now()
        val year = currentDate.year
        val month = currentDate.monthValue

        CalendarComponent(
            year = year,
            month = month,
            selectedDay = LocalDate.now(),
            onChangeSelectedDay = {},
        )
    }
}