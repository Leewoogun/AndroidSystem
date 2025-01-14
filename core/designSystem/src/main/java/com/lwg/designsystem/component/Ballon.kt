package com.lwg.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.lwg.designsystem.R
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.LwgTypo
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.rememberBalloonBuilder

@Composable
fun LwgBalloon(
    backgroundColor: Color,
    balloonContent: @Composable () -> Unit,
    balloonLayout: @Composable RowScope.() -> Unit,
    padding: Int = 12,
    horizontalMargin: Int = 12,
    radius: Float = 8f
) {
    val builder = rememberBalloonBuilder {
        setArrowSize(10)
        setArrowPosition(0.5f)
        setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        setWidth(BalloonSizeSpec.WRAP)
        setHeight(BalloonSizeSpec.WRAP)
        setPadding(padding)
        setMarginHorizontal(horizontalMargin)
        setCornerRadius(radius)
        setBackgroundColor(backgroundColor.toArgb())
        setBalloonAnimation(BalloonAnimation.ELASTIC)
    }

    Balloon(
        builder = builder,
        balloonContent = balloonContent
    ) { balloonWindow ->

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent, // 배경색 투명
                contentColor = Color.Black // 텍스트 색상 (필요에 따라 변경 가능)
            ),
            onClick = { balloonWindow.showAlignTop() },
            content = balloonLayout
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun LwgBalloonPreview() {
    LwgTheme {
        LwgBalloon(
            backgroundColor = MaterialTheme.colorScheme.tertiary,
            balloonContent = {
                Text(
                    text = "끌어서 즐겨찾기",
                    style = LwgTypo.typography.bodySmallR
                )
            },
            balloonLayout = {
                Icon(
                    modifier = Modifier,
                    painter = painterResource(R.drawable.ic_favorite_color),
                    contentDescription = null
                )
            }
        )
    }
}
