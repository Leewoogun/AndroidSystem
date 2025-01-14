package com.lwg.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.model.movie.Movie
import com.lwg.ui.DraggableItem
import com.lwg.ui.MovieCard
import com.lwg.ui.createAnchoredDraggableState
import kotlin.math.roundToInt

@Composable
internal fun HomeScreen(
    movieList: List<Movie>
) {
    LazyColumn {
        items(movieList) { movie ->
            SwipeMovieItem(movie)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SwipeMovieItem(
    movie: Movie,
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
                    .background(color = MaterialTheme.colorScheme.primary)
                    .onGloballyPositioned { coordinate ->
                        movieCardHeight = coordinate.size.height
                    },
                imageUrl = "https://image.tmdb.org/t/p/w500${movie.imageUrlEndPoint}",
                title = movie.title,
                genre = movie.genreList,
            )
        },
        endAction = {
            Row(
                modifier = Modifier
                    .height(with(LocalDensity.current) { movieCardHeight.toDp() }) // movieCard Height 저장
                    .align(Alignment.CenterEnd)
                    .offset {
                        IntOffset(
                            ((-anchoredDraggableState
                                .requireOffset()) + endActionSizePx)
                                .roundToInt(), 0
                        )
                    }
            ) {
                Box(
                    modifier = Modifier
                        .width(defaultActionSize)
                        .fillMaxHeight()
                        .background(color = MaterialTheme.colorScheme.tertiaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier,
                        painter = painterResource(R.drawable.ic_favorite_bottom),
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    LwgTheme {
        HomeScreen(
            movieList = listOf(
                Movie(
                    imageUrlEndPoint = "/9REO1DLpmwhrBJY3mYW5eVxkXFM.jpg",
                    title = "겨울 왕국",
                    genreList = listOf("판타지", "드라마")
                )
            )
        )
    }
}

