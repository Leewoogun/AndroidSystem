package com.lwg.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LwgProgressIndicator(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment
    ) {
        CircularProgressIndicator(
            color = color
        )
    }
}