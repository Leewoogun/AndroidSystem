package com.lwg.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.lwg.designsystem.R
import com.lwg.designsystem.theme.LwgTheme

@Composable
fun LwgIcon(
    modifier: Modifier,
    iconType: IconType,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        when (iconType) {
            is IconType.Default -> {
                Icon(
                    painter = painterResource(id = iconType.iconRes),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }

            is IconType.ImageVector -> {
                Icon(
                    imageVector = iconType.imageVector,
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    }
}

sealed interface IconType {
    data class Default(@DrawableRes val iconRes: Int): IconType
    data class ImageVector(val imageVector: androidx.compose.ui.graphics.vector.ImageVector): IconType
}

@Composable
@Preview
private fun LwgIconPreview() {
    LwgTheme {
        LwgIcon(
            modifier = Modifier,
            iconType = IconType.Default(R.drawable.ic_favorite_color)
        )
    }
}