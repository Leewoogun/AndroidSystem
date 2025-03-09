package com.lwg.ui.calendar

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color

enum class DayOfWeek(
    val title: String,
    val index: Int
) {
    MONDAY("월",  1),
    TUESDAY("화", 2),
    WEDNESDAY("수",3),
    THURSDAY("목", 4),
    FRIDAY("금", 5),
    SATURDAY("토", 6),
    SUNDAY("일", 0)
}