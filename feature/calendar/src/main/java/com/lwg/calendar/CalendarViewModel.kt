package com.lwg.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lwg.data_api.MovieRepository
import com.lwg.model.movie.Movie
import com.lwg.ui.calendar.CalendarDialogEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
internal class CalendarViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {
    private val _calendarState = MutableStateFlow(Calendar())
    val calendarState = _calendarState.asStateFlow()

    private val _calendarDialogEffect = MutableStateFlow<CalendarDialogEffect>(CalendarDialogEffect.Idle)
    val calendarDialogEffect = _calendarDialogEffect.asStateFlow()

    val favoriteMovie: StateFlow<List<Movie>> = calendarState.flatMapLatest { calendar ->
        movieRepository.getFavoriteMovie().map { favoriteMovieList ->
            favoriteMovieList.filter {
                it.insertTime == calendar.selectedDay
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

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

    fun onSelectDay(date: LocalDate) {
        _calendarState.update {
            it.copy(selectedDay = date)
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
    val month: Int = LocalDate.now().monthValue,
    val selectedDay: LocalDate = LocalDate.now()
)