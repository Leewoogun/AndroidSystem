package com.lwg.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @param velocityThresholdDp 1초에 [velocityThresholdDp] 이상 움직이면 드래그 처리가 됩니다.(스냅)
 * @param positionalThresholdWeight [dragThresholdsDp] * [positionalThresholdWeight] 이상 움직이면 드래그
 * 처리가 됩니다.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun createAnchoredDraggableState(
    velocityThresholdDp: Float,
    positionalThresholdWeight: Float,
    defaultActionSize : Dp = 80.dp
): AnchoredDraggableState<DragValue>{
    val density = LocalDensity.current
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()

    return remember {
        AnchoredDraggableState(
            anchors = DraggableAnchors {
                DragValue.Start at 0f
                DragValue.Center at 0f
                DragValue.End at with(density) { defaultActionSize.toPx() }
            },
            initialValue = DragValue.Start,
            positionalThreshold = { distance: Float -> distance * positionalThresholdWeight },
            velocityThreshold = { with(density) { velocityThresholdDp.dp.toPx() } },
            snapAnimationSpec = tween(),
            decayAnimationSpec = decayAnimationSpec,
            confirmValueChange = { newValue ->
                when (newValue) {
                    DragValue.Start -> true
                    DragValue.Center -> true
                    DragValue.End -> true
                }
            }
        )
    }
}

enum class DragValue { Start, Center, End }