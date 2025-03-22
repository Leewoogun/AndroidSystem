package com.lwg.calendar.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.component.IconType
import com.lwg.designsystem.component.LwgIcon
import com.lwg.designsystem.component.LwgImage
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.LwgTypo
import com.lwg.model.movie.Movie

@Composable
internal fun MovieCalendar(
    movie: Movie
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LwgIcon(
            modifier = Modifier
                .size(10.dp),
            iconType = IconType.ImageVector(Icons.Default.Circle)
        )

        LwgImage(
            modifier = Modifier
                .size(42.dp)
                .padding(horizontal = 5.dp),
            imageUrl = movie.imageUrl
        )

        Text(
            text = movie.title,
            style = LwgTypo.typography.bodyLargeR
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun MovieCalendarPreview() {
    LwgTheme {
        MovieCalendar(
            movie = Movie(
                imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                title = "겨울 왕국",
                genreList = listOf()
            )
        )
    }
}