package com.lwg.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.component.LwgCenterAlignedTopAppBar
import com.lwg.designsystem.component.LwgElevatedSelectedChip
import com.lwg.designsystem.component.LwgHorizontalDivider
import com.lwg.designsystem.component.LwgVerticalSpacer
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.favorite.contract.FavoriteUiState
import com.lwg.model.movie.Movie
import com.lwg.ui.FavoriteCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FavoriteScreen(
    uiState: FavoriteUiState.Movies
) {
    Scaffold(
        topBar = {
            LwgCenterAlignedTopAppBar(
                title = stringResource(R.string.favorite_topBar_title)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            LwgHorizontalDivider(
                color = MaterialTheme.colorScheme.primary
            )

            LwgVerticalSpacer(10.dp)

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(uiState.genreChipList) { index, genre ->
                    LwgElevatedSelectedChip(
                        selected = genre.isSelected,
                        title = genre.name,
                        onClick = {},
                        modifier = Modifier
                            .padding(start = if (index == 0) 12.dp else 0.dp) // ✅ 첫 번째 아이템만 start padding 적용
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(uiState.movieList) { movie ->
                    FavoriteCard(
                        imageUrl = movie.imageUrl,
                        title = movie.title
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun FavoriteScreenPreview() {
    LwgTheme {
        FavoriteScreen(
            uiState = FavoriteUiState.Movies(
                movieList = listOf(
                    Movie(
                        imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                        title = "겨울 왕국",
                        genreList = listOf("판타지", "드라마")
                    ),
                    Movie(
                        imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                        title = "겨울 왕국",
                        genreList = listOf("판타지", "드라마")
                    ),
                    Movie(
                        imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                        title = "겨울 왕국",
                        genreList = listOf("판타지", "드라마")
                    ),
                    Movie(
                        imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                        title = "겨울 왕국",
                        genreList = listOf("판타지", "드라마")
                    ),
                    Movie(
                        imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                        title = "겨울 왕국",
                        genreList = listOf("판타지", "드라마")
                    ),
                    Movie(
                        imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                        title = "겨울 왕국",
                        genreList = listOf("판타지", "드라마")
                    ),
                    Movie(
                        imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                        title = "겨울 왕국",
                        genreList = listOf("판타지", "드라마")
                    ),
                    Movie(
                        imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                        title = "겨울 왕국",
                        genreList = listOf("판타지", "드라마")
                    ),
                    Movie(
                        imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                        title = "겨울 왕국",
                        genreList = listOf("판타지", "드라마")
                    ),
                    Movie(
                        imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                        title = "겨울 왕국",
                        genreList = listOf("판타지", "드라마")
                    ),
                    Movie(
                        imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                        title = "겨울 왕국",
                        genreList = listOf("판타지", "드라마")
                    ),
                )
            )
        )
    }
}