package com.lwg.calendar.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.lwg.calendar.CalendarRoute
import com.lwg.navigation.MainBottomBarRoute

fun NavController.navigateToCalendar(
    navOptions: NavOptions? = null
) {
    navigate(MainBottomBarRoute.Calendar, navOptions)
}

fun NavGraphBuilder.calendarNavGraph(
    onBackEvent: () -> Unit,
    onShowSnackBar: (String) -> Unit
) {
    composable<MainBottomBarRoute.Calendar> {
        CalendarRoute()
    }
}