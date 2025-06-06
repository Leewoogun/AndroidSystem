package com.lwg.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.component.LwgBalloon
import com.lwg.designsystem.component.LwgImage
import com.lwg.designsystem.component.LwgImage2
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.LwgTypo

@Composable
fun MovieCard(
    imageUrl: String,
    title: String,
    genre: List<String>,
    balloonText: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LwgImage2(
            modifier = Modifier.size(40.dp),
            imageUrl = imageUrl
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = genre.joinToString(),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        LwgBalloon(
            backgroundColor = MaterialTheme.colorScheme.tertiary,
            balloonContent = {
                Text(
                    text = balloonText,
                    style = LwgTypo.typography.bodySmallR
                )
            },
            balloonLayout = {
                Icon(
                    painter = painterResource(R.drawable.ic_mark),
                    contentDescription = null,
                )
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun MovieCardPreview() {
    LwgTheme {
        MovieCard(
            imageUrl = "https://picsum.photos/200",
            title = "겨울 왕국",
            genre = listOf("판타지", "드라마"),
            balloonText = "끌어서 즐겨찾기",
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}