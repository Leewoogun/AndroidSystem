package com.lwg.designsystem.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object ComposeUtil {
    // 휴대폰 화면 너비 가져오기
    @Composable
    fun getScreenWidthInDp(): Dp {
        return LocalConfiguration.current.screenWidthDp.dp
    }
}