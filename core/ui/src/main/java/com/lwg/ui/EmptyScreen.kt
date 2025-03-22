package com.lwg.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.LwgTypo

@Composable
fun EmptyScreen(
    title: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = LwgTypo.typography.titleNormalM
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun EmptyScreenPreview() {
    LwgTheme {
        EmptyScreen(title = "해당 날짜에 추가한 영화가 없습니다.")
    }
}