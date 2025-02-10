package com.lwg.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent

@Composable
fun LwgImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    CoilImage(
        modifier = modifier,
        imageModel = { imageUrl },
        component = rememberImageComponent {
            +CrossfadePlugin(
                duration = 550
            )
        },
        loading = { LoadingImage(modifier) },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        ),
    )
}

@Composable
fun LoadingImage(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.secondary)
    ) {

    }
}

@Composable
@Preview(showBackground = true)
fun LoadingImagePreview() {
    LoadingImage(
        modifier = Modifier.size(120.dp)
    )
}

@Composable
@Preview(showBackground = true)
fun LwgImagePreview() {
    LwgImage(
        imageUrl = "https://picsum.photos/200",
        modifier = Modifier.size(120.dp)
    )
}