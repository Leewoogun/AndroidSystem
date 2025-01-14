package com.lwg.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.R
import com.lwg.designsystem.theme.LwgTheme

@Composable
fun SwipeBackgroundUi(
    @DrawableRes iconId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
) {
    Box(
        modifier = modifier
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .clickable { onClick() },
            painter = painterResource(iconId),
            contentDescription = null
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun SwipeBackgroundUiPreview() {
    LwgTheme {
        SwipeBackgroundUi(
            modifier = Modifier.size(50.dp),
            iconId = R.drawable.ic_favorite_color,
            onClick = {}
        )
    }
}