@file:OptIn(ExperimentalMaterial3Api::class)

package com.lwg.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.LwgTypo
import com.lwg.designsystem.util.ComposeUtil

@Composable
fun LwgDefaultDialog(
    modifier: Modifier = Modifier,
    title: String = "",
    contentHorizontalPadding: Dp = 10.dp,
    contentVerticalSpacing: Dp = 10.dp,
    onDismissRequest: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit = {},
    button1: @Composable RowScope.() -> Unit = {},
    button2: (@Composable RowScope.() -> Unit)? = null,
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
        ) {
            Column(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.onSecondary)
            ) {
                if (title.isNotEmpty()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        text = title,
                        style = LwgTypo.typography.titleNormalB,
                        textAlign = TextAlign.Center,
                    )
                }

                LwgHorizontalDivider()

                LwgVerticalSpacer(height = contentVerticalSpacing)

                Column(
                    modifier = Modifier
                        .padding(horizontal = contentHorizontalPadding)
                ) {
                    content()
                    LwgVerticalSpacer(height = contentVerticalSpacing)
                    Row(
                        modifier = Modifier
                            .padding(vertical = contentVerticalSpacing)
                    ) {
                        button1()
                        button2?.let {
                            LwgHorizontalSpacer(width = 10.dp)
                            it()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LwgBottomSheet(
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    title: String = "",
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    sheetMaxWidth: Dp = ComposeUtil.getScreenWidthInDp()
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        sheetMaxWidth = sheetMaxWidth
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = LwgTypo.typography.titleNormalB,
            )

            LwgVerticalSpacer(height = 10.dp)

            content()
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun LwgDefaultDialogPreview() {
    LwgTheme {
        LwgDefaultDialog(
            title = "다이얼로그 제목",
            content = {
                Text(
                    text = "다이얼로그 내용",
                    style = LwgTypo.typography.titleMediumR
                )
            },
            button2 = {
                LwgButton(
                    modifier = Modifier.weight(1f),
                    buttonText = "확인",
                    onClick = {}
                )
            },
            button1 = {
                LwgButton(
                    modifier = Modifier.weight(1f),
                    buttonText = "취소",
                    onClick = {},
                    isEnabled = false
                )
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun LwgBottomSheetPreview() {
    LwgTheme {
        LwgBottomSheet(
            sheetState = SheetState(true, Density(1f), SheetValue.Expanded, { true }, false),
            onDismissRequest = {},
            title = "바텀 시트 제목",
            content = {
                Column(
                    modifier = Modifier.padding(vertical = 10.dp)
                ) {
                    Text(
                        text = "바텀 시트 내용",
                        style = LwgTypo.typography.titleMediumR,
                    )
                }
            }
        )
    }
}