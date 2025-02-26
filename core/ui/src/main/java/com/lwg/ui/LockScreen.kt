package com.lwg.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lwg.designsystem.component.IconType
import com.lwg.designsystem.component.LwgIcon
import com.lwg.designsystem.theme.LwgTheme

@Composable
fun LockScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LwgIcon(
            modifier = Modifier,
            iconType = IconType.Default(R.drawable.ic_lock)
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun LockScreenPreview() {
    LwgTheme {
        LockScreen()
    }
}