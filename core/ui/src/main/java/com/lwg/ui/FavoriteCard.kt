package com.lwg.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.component.LwgImage
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.LwgTypo

@Composable
fun FavoriteCard(
    imageUrl: String,
    title: String,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary
        ),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LwgImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                imageUrl = imageUrl
            )

            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                text = title,
                style = LwgTypo.typography.bodyMediumR,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Icon(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .clickable {
                        // TODO favorite 삭제 event
                    },
                painter = painterResource(R.drawable.ic_favorite_color),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun FavoriteCardPreview() {
    LwgTheme {
        FavoriteCard(
            modifier = Modifier
                .width(200.dp),
            imageUrl = "https://picsum.photos/200",
            title = "겨울 왕국"
        )
    }
}