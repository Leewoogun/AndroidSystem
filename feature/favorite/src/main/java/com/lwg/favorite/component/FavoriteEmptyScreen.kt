package com.lwg.favorite.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.LwgTypo
import com.lwg.favorite.R

@Composable
fun FavoriteEmptyScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.favorite_no_movie),
            style = LwgTypo.typography.headlineSmallR
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun FavoriteEmptyScreenPreview() {
    LwgTheme {
        FavoriteEmptyScreen()
    }
}