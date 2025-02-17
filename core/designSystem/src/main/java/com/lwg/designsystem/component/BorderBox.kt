package com.lwg.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.LwgTypo

@Composable
fun LwgBorderBox(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    horizontalPadding: Dp = 15.dp,
    verticalPadding: Dp = 10.dp,
) {
    Surface(
        modifier = modifier,
        shape = shape,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        Box(
            modifier = Modifier
                .padding(
                    horizontal = horizontalPadding,
                    vertical = verticalPadding
                ),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
@Preview
private fun LwgDefaultChipPreview() {
    LwgTheme {
        LwgBorderBox(
            content = {
                Text(
                    text = "title",
                    style = LwgTypo.typography.titleSmallM
                )
            }
        )
    }
}