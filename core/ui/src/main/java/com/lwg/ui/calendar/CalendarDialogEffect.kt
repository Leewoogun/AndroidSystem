package com.lwg.ui.calendar

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed interface CalendarDialogEffect {
    @Immutable
    data object Idle: CalendarDialogEffect

    @Immutable
    data object ShowYearMonthDialog: CalendarDialogEffect
}