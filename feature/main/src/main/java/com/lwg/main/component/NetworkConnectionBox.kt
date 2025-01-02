package com.lwg.main.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.component.TopToBottomToTopAnimatedVisibility
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.primaryContainerLight
import com.lwg.main.R

@Composable
fun NetworkConnectionBox(
    networkState: Boolean,
    modifier: Modifier = Modifier
) {
    TopToBottomToTopAnimatedVisibility(
        visible = !networkState
    ) {
        Surface(
            modifier = modifier
                .padding(10.dp),
            color = primaryContainerLight,
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 5.dp),
                    text = stringResource(id = R.string.error_message_network)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NetworkConnectionBoxPreview() {
    LwgTheme {
        NetworkConnectionBox(
            networkState = false,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}