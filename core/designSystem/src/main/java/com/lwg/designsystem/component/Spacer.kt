package com.lwg.designsystem.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun LwgHorizontalSpacer(
    width: Dp
) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun LwgVerticalSpacer(
    height: Dp
) {
    Spacer(modifier = Modifier.height(height))
}