package com.lwg.ui.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.component.IconType
import com.lwg.designsystem.component.LwgHorizontalDivider
import com.lwg.designsystem.component.LwgIcon
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.LwgTypo
import java.time.LocalDate

@Composable
fun CalendarTopBar(
    year: Int,
    month: Int,
    onShowYearMonthDialog: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LwgIcon(
                modifier = Modifier
                    .size(48.dp),
                iconType = IconType.ImageVector(Icons.Default.Menu),
            )

            YearMonthText(year, month)

            LwgIcon(
                modifier = Modifier
                    .size(36.dp),
                iconType = IconType.ImageVector(Icons.Default.KeyboardArrowDown),
                onClick = onShowYearMonthDialog
            )
        }
        LwgHorizontalDivider()
    }
}

@Composable
private fun YearMonthText(year: Int, month: Int) {
    val text = "${year}.${month}"

    Row {
        Text(
            text = text,
            style = LwgTypo.typography.displaySmallB
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun CalendarTopBarPreview() {
    LwgTheme {
        val currentDate = LocalDate.now()
        val year = currentDate.year
        val month = currentDate.monthValue
        
        CalendarTopBar(
            year = year,
            month = month,
            onShowYearMonthDialog = {}
        )
    }
}