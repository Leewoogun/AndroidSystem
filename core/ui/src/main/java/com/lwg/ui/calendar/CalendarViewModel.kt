package com.lwg.ui.calendar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(

): ViewModel() {
    private val _calendarState = MutableStateFlow(Calendar())
    val calendarState = _calendarState.asStateFlow()

    private val _calendarDialogEffect = MutableStateFlow<CalendarDialogEffect>(CalendarDialogEffect.Idle)
    val calendarDialogEffect = _calendarDialogEffect.asStateFlow()

    fun onSelectYear(year: Int) {
        _calendarState.update {
            it.copy(year = year)
        }
    }

    fun onSelectMonth(month: Int) {
        _calendarState.update {
            it.copy(month = month)
        }
    }

    fun dismissDialog() {
        _calendarDialogEffect.update { CalendarDialogEffect.Idle }
    }

    fun showYearMonthDialog() {
        _calendarDialogEffect.update {
            CalendarDialogEffect.ShowYearMonthDialog
        }
    }
}

data class Calendar(
    val year: Int = LocalDate.now().year,
    val month: Int = LocalDate.now().monthValue
)