package com.lwg.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun LwgFadeAnimatedVisibility(
    visible: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable() AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300)),
        content = content
    )
}

@Composable
fun TopToBottomToTopAnimatedVisibility(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(300)
        ),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(300)
        ),
        content = content
    )
}

@Composable
fun LoadingProgressIndicatorAnimation(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { it }, // Slide from the bottom
            animationSpec = tween(durationMillis = 300) // Customize the duration
        ),
        exit = slideOutVertically(
            targetOffsetY = { it }, // Slide to the bottom
            animationSpec = tween(durationMillis = 300) // Customize the duration
        )
    ) {
        content() // Calling the content lambda
    }
}

@Composable
fun rotate180Angle(
    isRotate: Boolean
): Float = animateFloatAsState(targetValue = if (isRotate) 180f else 0f, label = "label").value