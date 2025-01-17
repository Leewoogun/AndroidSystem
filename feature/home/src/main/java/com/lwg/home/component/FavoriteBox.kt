package com.lwg.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.pink1
import com.lwg.home.R

@Composable
fun FavoriteBox(
    containerModifier: Modifier,
    defaultActionSize: Dp,
    isFavorite: Boolean,
    onClick: () -> Unit
) {
    val favoriteIcon = when (isFavorite) {
        true -> painterResource(R.drawable.ic_favorite_color)
        false -> painterResource(R.drawable.ic_favorite_bottom)
    }

    Row(
        modifier = containerModifier
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    onClick()
                }
                .width(defaultActionSize)
                .fillMaxHeight()
                .background(color = MaterialTheme.colorScheme.outline),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier,
                painter = favoriteIcon,
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun FavoriteBoxPreview() {
    LwgTheme {
        FavoriteBox(
            containerModifier = Modifier
                .height(80.dp),
            defaultActionSize = 80.dp,
            isFavorite = true,
            onClick = {}
        )
    }
}