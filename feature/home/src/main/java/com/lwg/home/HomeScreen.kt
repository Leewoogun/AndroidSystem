package com.lwg.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.component.LoadingProgressIndicatorAnimation
import com.lwg.designsystem.component.LwgProgressIndicator
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.home.component.FavoriteBox
import com.lwg.model.movie.Movie
import com.lwg.ui.DraggableItem
import com.lwg.ui.MovieCard
import com.lwg.ui.createAnchoredDraggableState
import kotlin.math.roundToInt

@Composable
internal fun HomeScreen(
    movieList: List<Movie>,
    lazyListState: LazyListState,
    isLoading: Boolean,
    onFavoriteClick: (Movie) -> Unit,
    onMovieClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = lazyListState
        ) {
            items(movieList) { movie ->
                SwipeMovieItem(
                    movie = movie,
                    onFavoriteClick = onFavoriteClick,
                    onMovieClick = onMovieClick
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
        ) {
            LoadingProgressIndicatorAnimation(
                visible = isLoading
            ) {
                LwgProgressIndicator(
                    color = MaterialTheme.colorScheme.primaryContainer
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SwipeMovieItem(
    movie: Movie,
    onFavoriteClick: (Movie) -> Unit,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val anchoredDraggableState = createAnchoredDraggableState(
        velocityThresholdDp = 100f,
        positionalThresholdWeight = 0.3f
    )
    val density = LocalDensity.current
    val defaultActionSize : Dp = 80.dp
    val endActionSizePx = with(density) { (defaultActionSize).toPx() }

    var movieCardHeight by remember { mutableIntStateOf(0) } // movieCard 높이 측정

    DraggableItem(
        state = anchoredDraggableState,
        content = {
            MovieCard(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
                    .onGloballyPositioned { coordinate ->
                        movieCardHeight = coordinate.size.height
                    }
                    .clickable {
                        onMovieClick(movie.movieId)
                    },
                imageUrl = movie.imageUrl,
                title = movie.title,
                genre = movie.genreList.map { it.name },
                balloonText = stringResource(R.string.balloon_text)
            )
        },
        endAction = {
            FavoriteBox(
                containerModifier = Modifier
                    .height(with(LocalDensity.current) { movieCardHeight.toDp() }) // movieCard Height 저장
                    .align(Alignment.CenterEnd)
                    .offset {
                        IntOffset(
                            ((-anchoredDraggableState
                                .requireOffset()) + endActionSizePx)
                                .roundToInt(), 0
                        )
                    },
                defaultActionSize = defaultActionSize,
                isFavorite = movie.isFavorite,
                onClick = {
                    onFavoriteClick(movie)
                }
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    LwgTheme {
        HomeScreen(
            lazyListState = rememberLazyListState(),
            isLoading = true,
            movieList = listOf(
                Movie(
                    imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                    title = "겨울 왕국",
                    genreList = listOf()
                ),
                Movie(
                    imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                    title = "겨울 왕국",
                    genreList = listOf()
                ),
                Movie(
                    imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                    title = "겨울 왕국",
                    genreList = listOf()
                ),
                Movie(
                    imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                    title = "겨울 왕국",
                    genreList = listOf()
                ),Movie(
                    imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                    title = "겨울 왕국",
                    genreList = listOf()
                ),Movie(
                    imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                    title = "겨울 왕국",
                    genreList = listOf()
                ),Movie(
                    imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                    title = "겨울 왕국",
                    genreList = listOf()
                ),
                Movie(
                    imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                    title = "겨울 왕국",
                    genreList = listOf()
                ),
                Movie(
                    imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                    title = "겨울 왕국",
                    genreList = listOf()
                ),
                Movie(
                    imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                    title = "겨울 왕국",
                    genreList = listOf()
                ),
                Movie(
                    imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                    title = "겨울 왕국",
                    genreList = listOf()
                ),
            ),
            onFavoriteClick = {},
            onMovieClick = {}
        )
    }
}

