package com.lwg.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

val TextUnit.nonScaledSp
    @Composable
    @ReadOnlyComposable
    get() = (this.value / LocalDensity.current.fontScale).sp

val TextStyle.nonScaledSp
    @Composable
    @ReadOnlyComposable
    get() = this.copy(
        fontSize = this.fontSize.nonScaledSp,
        lineHeight = this.lineHeight.nonScaledSp
    )

val Typography = LwgTypography(
    displayLargeB = TextStyle(
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.Bold
    ),
    displayMediumB = TextStyle(
        fontSize = 30.sp,
        lineHeight = 38.sp,
        fontWeight = FontWeight.Bold,
    ),
    displaySmallB = TextStyle(
        fontSize = 28.sp,
        lineHeight = 34.sp,
        fontWeight = FontWeight.Bold,
    ),
    headlineLargeB = TextStyle(
        fontSize = 26.sp,
        lineHeight = 34.sp,
        fontWeight = FontWeight.Bold,
    ),
    headlineMediumB = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Bold,
    ),
    headlineMediumM = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Medium,
    ),
    headlineMediumR = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
    ),
    headlineSmallB = TextStyle(
        fontSize = 22.sp,
        lineHeight = 30.sp,
        fontWeight = FontWeight.Bold,
    ),
    headlineSmallM = TextStyle(
        fontSize = 22.sp,
        lineHeight = 30.sp,
        fontWeight = FontWeight.Medium,
    ),
    headlineSmallR = TextStyle(
        fontSize = 22.sp,
        lineHeight = 30.sp,
    ),
    titleLargeB = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Bold,
    ),
    titleLargeM = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Medium,
    ),
    titleLargeR = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
    ),

    titleNormalB = TextStyle(
        fontSize = 18.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight.Bold,
    ),
    titleNormalM = TextStyle(
        fontSize = 18.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight.Medium,
    ),
    titleNormalR = TextStyle(
        fontSize = 18.sp,
        lineHeight = 26.sp,
    ),
    titleMediumB = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold,
    ),
    titleMediumM = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Medium,
    ),
    titleMediumR = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    titleSmallB = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.25.sp,
    ),
    titleSmallM = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.25.sp,
    ),
    titleSmallR = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    labelLargeB = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Bold,
    ),
    labelLargeM = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
    ),
    labelLargeR = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    labelMediumB = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 14.sp,
    ),
    labelMediumM = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 14.sp,
    ),
    labelMediumR = TextStyle(
        fontSize = 10.sp,
        lineHeight = 14.sp,
    ),
    labelSmallM = TextStyle(
        fontSize = 11.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = (-0.2).sp,
    ),
    bodyLargeR = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    bodyMediumR = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
    ),
    bodySmallR = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )
)

@Immutable
data class LwgTypography(
    val displayLargeB: TextStyle,
    val displayMediumB: TextStyle,
    val displaySmallB: TextStyle,

    val headlineLargeB: TextStyle,
    val headlineMediumB: TextStyle,
    val headlineMediumM: TextStyle,
    val headlineMediumR: TextStyle,
    val headlineSmallB: TextStyle,
    val headlineSmallM: TextStyle,
    val headlineSmallR: TextStyle,

    val titleLargeB: TextStyle,
    val titleLargeM: TextStyle,
    val titleLargeR: TextStyle,
    val titleNormalB: TextStyle,
    val titleNormalM: TextStyle,
    val titleNormalR: TextStyle,
    val titleMediumB: TextStyle,
    val titleMediumM: TextStyle,
    val titleMediumR: TextStyle,
    val titleSmallB: TextStyle,
    val titleSmallM: TextStyle,
    val titleSmallR: TextStyle,

    val labelLargeB: TextStyle,
    val labelLargeM: TextStyle,
    val labelLargeR: TextStyle,
    val labelMediumB: TextStyle,
    val labelMediumM: TextStyle,
    val labelMediumR: TextStyle,
    val labelSmallM: TextStyle,

    val bodyLargeR: TextStyle,
    val bodyMediumR: TextStyle,
    val bodySmallR: TextStyle,
)