package com.lwg.designsystem.component

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.theme.Black
import com.lwg.designsystem.theme.Gray1
import com.lwg.designsystem.theme.LwgTheme

@Composable
fun LwgHorizontalDivider(
    modifier : Modifier = Modifier,
    thickness: Dp = 1.dp,
    color: Color = Color.Red
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )
}

@Composable
@Preview(showBackground = true)
fun LwgHorizontalDividerPreview() {
    LwgTheme {
        LwgHorizontalDivider()
    }
}