package com.lwg.designsystem.component

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.theme.LwgTheme

@Composable
fun LwgHorizontalDivider(
    thickness: Dp = 1.dp
) {
    HorizontalDivider(
        thickness = thickness
    )
}

@Composable
@Preview(showBackground = true)
fun LwgHorizontalDividerPreview() {
    LwgTheme {
        LwgHorizontalDivider()
    }
}