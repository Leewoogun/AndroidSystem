package com.lwg.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.theme.Black
import com.lwg.designsystem.theme.Gray1
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.LwgTypo
import com.lwg.designsystem.theme.White
import com.lwg.designsystem.theme.main

@Composable
fun LwgButton(
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    radius: Dp = 4.dp,
    contentVerticalPadding: Dp = 10.dp,
    color: Color = main,
    isEnabled: Boolean = true
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(radius),
        enabled = isEnabled,
        contentPadding = PaddingValues(contentVerticalPadding),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            disabledContainerColor = Gray1
        ),
        onClick = onClick
    ) {
        Text(
            text = buttonText,
            style = LwgTypo.typography.titleMediumB
        )
    }
}

@Composable
fun LwgOutlinedButton(
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = Black,
    isActiveColor: Color = main,
    isActiveTextColor: Color = White,
    isDisableTextColor: Color = Black,
    radius: Dp = 4.dp,
    isEnabled: Boolean = true,
) {
    OutlinedButton(
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            disabledContainerColor = White,
            disabledContentColor = isDisableTextColor,
            containerColor = isActiveColor,
            contentColor = isActiveTextColor
        ),
        shape = RoundedCornerShape(radius),
        border = BorderStroke(width = 1.dp, color = borderColor),
        enabled = isEnabled,
        onClick = onClick
    ) {
        Text(
            text = buttonText,
            style = LwgTypo.typography.titleMediumB
        )
    }
}

@Composable
@Preview(showBackground = false)
private fun LwgButtonPreview() {
    LwgTheme {
        LwgButton(
            modifier = Modifier
                .fillMaxWidth(),
            buttonText = "확인",
            onClick = {}
        )
    }
}

@Composable
@Preview(showBackground = false)
private fun LwgDisabledButtonPreview() {
    LwgTheme {
        LwgButton(
            modifier = Modifier
                .fillMaxWidth(),
            buttonText = "확인",
            onClick = {},
            isEnabled = false
        )
    }
}

@Composable
@Preview
private fun LwgOutlinedButtonPreview() {
    LwgTheme {
        LwgOutlinedButton(
            modifier = Modifier,
            buttonText = "윤곽선 버튼",
            onClick = {}
        )
    }
}