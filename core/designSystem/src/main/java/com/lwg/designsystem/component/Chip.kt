package com.lwg.designsystem.component

import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.lwg.designsystem.theme.LwgTheme

@Composable
fun LwgElevatedSelectedChip(
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    ElevatedFilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = title
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
private fun LwgElevatedSelectedChipPreview() {
    LwgTheme {
        LwgElevatedSelectedChip(
            title = "title",
            selected = true,
            onClick = {}
        )
    }
}