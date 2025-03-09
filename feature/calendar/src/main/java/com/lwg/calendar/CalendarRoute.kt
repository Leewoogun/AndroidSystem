package com.lwg.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults.windowInsets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lwg.ui.calendar.Calendar
import com.lwg.ui.calendar.CalendarComponent
import com.lwg.ui.calendar.CalendarDialogEffect
import com.lwg.ui.calendar.CalendarTopBar
import com.lwg.ui.calendar.CalendarViewModel
import com.lwg.ui.calendar.CalendarYearMonthDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CalendarRoute(
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val calendarState by viewModel.calendarState.collectAsStateWithLifecycle()
    val calendarDialogEffect by viewModel.calendarDialogEffect.collectAsStateWithLifecycle()

    DialogContent(
        calendarDialogEffect = calendarDialogEffect,
        calendarState = calendarState,
        onSelectYear = viewModel::onSelectYear,
        onSelectMonth = viewModel::onSelectMonth,
        onDismissRequest = viewModel::dismissDialog
    )

    Scaffold (
        modifier = Modifier
            .windowInsetsPadding(windowInsets),
        topBar = {
            CalendarTopBar(
                year = calendarState.year,
                month = calendarState.month,
                onShowYearMonthDialog = viewModel::showYearMonthDialog
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            CalendarComponent(
                calendarState.year,
                calendarState.month
            )
        }
    }
}

@Composable
internal fun DialogContent(
    calendarDialogEffect: CalendarDialogEffect,
    calendarState: Calendar,
    onSelectYear: (Int) -> Unit,
    onSelectMonth: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    when (calendarDialogEffect) {
        CalendarDialogEffect.Idle -> {}
        CalendarDialogEffect.ShowYearMonthDialog -> {
            CalendarYearMonthDialog(
                year = calendarState.year,
                month = calendarState.month,
                onSelectYear = onSelectYear,
                onSelectMonth = onSelectMonth,
                onDismissRequest = onDismissRequest
            )
        }
    }
}