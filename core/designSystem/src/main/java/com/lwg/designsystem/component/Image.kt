package com.lwg.designsystem.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun LwgImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    CoilImage(
        modifier = modifier
            .aspectRatio(1f),
        imageModel = { imageUrl },
        loading = { LoadingImage(modifier) },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        ),
        previewPlaceholder = painterResource(R.drawable.ic_image_sample)
    )
}

@Composable
fun LoadingImage(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = Color.Black.copy(alpha = 0.5f)
    ) {

    }
}

@Composable
@Preview(showBackground = true)
fun LoadingImagePreview() {
    LoadingImage(
        modifier = Modifier.size(40.dp)
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