package com.lwg.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.lwg.designsystem.component.LwgButton
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.LwgTypo
import java.time.LocalDate

@Composable
fun CalendarYearMonthDialog(
    year: Int,
    month: Int,
    onSelectYear: (Int) -> Unit,
    onSelectMonth: (Int) -> Unit,
    onDismissRequest: () -> Unit,
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = onDismissRequest
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column {
                CalendarYear(
                    selectedYear = year,
                    onSelectYear = onSelectYear
                )
                CalendarMonth(
                    selectedMonth = month,
                    onSelectMonth = onSelectMonth
                )
                YesNoButton(
                    onConfirm = onDismissRequest
                )
            }
        }
    }

}

@Composable
fun CalendarYear(
    selectedYear: Int,
    onSelectYear: (Int) -> Unit
) {
    val currentDate = LocalDate.now()
    val currentYear = currentDate.year
    val startYear = currentYear - 3
    val yearList = (startYear..currentYear).toList()

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        items(yearList) { year ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .clip(CircleShape)
                    .background(
                        if (year == selectedYear) MaterialTheme.colorScheme.primary else Color.Transparent,
                        CircleShape
                    )
                    .clickable { onSelectYear(year) }
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = year.toString(),
                    style = LwgTypo.typography.titleLargeM,
                    color = if (year == selectedYear) Color.White else Color.Black
                )
            }
        }
    }
}

@Composable
fun CalendarMonth(
    selectedMonth: Int,
    onSelectMonth: (Int) -> Unit
) {
    val monthList = (1..12).toList()

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(monthList) { month ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .clip(CircleShape)
                    .background(
                        if (month == selectedMonth) MaterialTheme.colorScheme.primary else Color.Transparent,
                        CircleShape
                    )
                    .clickable { onSelectMonth(month) }
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$month 월",
                    style = LwgTypo.typography.titleLargeM,
                    color = if (month == selectedMonth) Color.White else Color.Black
                )
            }
        }
    }
}

@Composable
fun YesNoButton(
    onConfirm: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
    ) {
        LwgButton(
            modifier = Modifier.weight(1f),
            buttonText = "확인",
            onClick = onConfirm
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun CalendarYearMonthDialogPreview() {
    LwgTheme {
        CalendarYearMonthDialog(
            year = LocalDate.now().year,
            month = LocalDate.now().monthValue,
            onSelectYear = {},
            onSelectMonth = {},
            onDismissRequest = {}
        )
    }
}