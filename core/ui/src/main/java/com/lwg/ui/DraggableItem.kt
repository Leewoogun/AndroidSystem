package com.lwg.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableItem(
    state: AnchoredDraggableState<DragValue>,
    content: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    endAction: @Composable (BoxScope.() -> Unit)? = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {

        endAction?.let {
            endAction()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .offset {
                    IntOffset(
                        x = -state
                            .requireOffset()
                            .roundToInt(),
                        y = 0,
                    )
                }
                .anchoredDraggable(state = state, orientation = Orientation.Horizontal, reverseDirection = true),
            content = content
        )
    }
}
