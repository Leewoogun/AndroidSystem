package coml.lwg.movie_detail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.component.FlexibleTopBar
import com.lwg.designsystem.component.IconType
import com.lwg.designsystem.component.LwgBorderBox
import com.lwg.designsystem.component.LwgHorizontalDivider
import com.lwg.designsystem.component.LwgHorizontalSpacer
import com.lwg.designsystem.component.LwgIcon
import com.lwg.designsystem.component.LwgImage
import com.lwg.designsystem.component.LwgTopAppBar
import com.lwg.designsystem.component.LwgVerticalSpacer
import com.lwg.designsystem.component.rotate180Angle
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.LwgTypo
import com.lwg.model.movie.Author
import com.lwg.model.movie.Genre
import com.lwg.model.movie.MovieDetail
import com.lwg.model.movie.Review
import com.lwg.model.movie.ReviewInfo
import com.lwg.util.Etc.convertDoubleToString
import coml.lwg.movie_detail.component.StarRatingBar
import coml.lwg.movie_detail.component.UserReviewComponent
import coml.lwg.movie_detail.contract.MovieDetailUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MovieDetailScreen(
    movieDetailUiState: MovieDetailUiState.MovieDetailData,
    reviewData: Review,
    isShowPosterImage: Boolean,
    onCollapsed: () -> Unit,
    onExpanded: () -> Unit,
    onBackEvent: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    val alphaPoster by animateFloatAsState(
        targetValue = if (isShowPosterImage) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
    )

    val alphaTopAppBar by animateFloatAsState(
        targetValue = if (!isShowPosterImage) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
    )

    var isExpandedOverview by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Box(
                modifier = Modifier.graphicsLayer { this.alpha = alphaPoster }
            ) {
                FlexibleTopBar(
                    scrollBehavior = scrollBehavior,
                    onCollapsed = onCollapsed,
                    onExpanded = onExpanded,
                    content = {
                        Box {
                            LwgImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp),
                                imageUrl = movieDetailUiState.movieDetail.posterImageUrl
                            )
                        }
                    },
                )
            }

            Box(
                modifier = Modifier.graphicsLayer { this.alpha = alphaTopAppBar }
            ) {
                LwgTopAppBar(
                    title = movieDetailUiState.movieDetail.movieTitle,
                    topBarIcon = {
                        Box(
                            modifier = Modifier
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier
                                    .clickable {
                                        onBackEvent()
                                    },
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    }
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(innerPadding)
                .padding(vertical = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = movieDetailUiState.movieDetail.movieTitle,
                    style = LwgTypo.typography.titleLargeM,
                    maxLines = 1
                )

                LwgVerticalSpacer(10.dp)

                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StarRatingBar(
                        rating = movieDetailUiState.movieDetail.voteAverage,
                    )

                    LwgHorizontalSpacer(10.dp)

                    Text(
                        text = convertDoubleToString(movieDetailUiState.movieDetail.voteAverage),
                        style = LwgTypo.typography.titleMediumR
                    )
                }
            }

            LwgHorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                thickness = 5.dp,
                color = MaterialTheme.colorScheme.primary
            )
            
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                items(movieDetailUiState.movieDetail.genres) {
                    LwgBorderBox(
                        modifier = Modifier
                            .padding(horizontal = 5.dp),
                        horizontalPadding = 25.dp,
                        shape = CircleShape,
                        content = {
                            Text(
                                text = it.name,
                                style = LwgTypo.typography.titleSmallR
                            )
                        }
                    )
                }
            }

            LwgVerticalSpacer(10.dp)

            LwgBorderBox(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .clickable {
                        isExpandedOverview = !isExpandedOverview
                    },
                shape = RoundedCornerShape(4.dp),
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "개요",
                                style = LwgTypo.typography.bodyLargeR
                            )

                            Spacer(
                                modifier = Modifier.weight(1f)
                            )

                            LwgIcon(
                                modifier = Modifier
                                    .size(24.dp)
                                    .rotate(rotate180Angle(isExpandedOverview)),
                                iconType = IconType.ImageVector(Icons.Default.KeyboardArrowDown),
                                iconColor = MaterialTheme.colorScheme.primary,
                                onClick = { isExpandedOverview = !isExpandedOverview }
                            )
                        }

                        if (isExpandedOverview) {
                            Column {
                                LwgVerticalSpacer(10.dp)

                                Text(
                                    text = movieDetailUiState.movieDetail.overview,
                                    style = LwgTypo.typography.bodyLargeR,
                                    maxLines = 3,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            )

            LwgVerticalSpacer(10.dp)

            LwgHorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                thickness = 5.dp,
                color = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "댓글",
                    style = LwgTypo.typography.titleLargeM
                )

                reviewData.reviewData.forEach { review ->
                    UserReviewComponent(
                        reviewInfo = review,
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun MovieDetailScreenPreview() {
    LwgTheme {
        MovieDetailScreen(
            MovieDetailUiState.MovieDetailData(
                MovieDetail(
                    movieTitle = "영화 제목",
                    overview = "영화 설명",
                    posterPathEndPoint = "",
                    voteAverage = 8.5678,
                    genres = listOf(
                        Genre(
                            id = 0,
                            name = "공포"
                        ),

                        Genre(
                            id = 0,
                            name = "공포"
                        )
                    )
                )
            ),
            reviewData = Review(
                page = 0,
                reviewData = listOf(
                    ReviewInfo(
                        authorInfo = Author(
                            name = "우건 닉네임",
                            userName = "우건 실제 네임",
                            profilePath = ""
                        ),
                        content = "재밌어요",
                        rating = 9.0
                    ),

                    ReviewInfo(
                        authorInfo = Author(
                            name = "우건 닉네임",
                            userName = "우건 실제 네임",
                            profilePath = ""
                        ),
                        content = "재밌어요",
                        rating = 9.0
                    ),

                    ReviewInfo(
                        authorInfo = Author(
                            name = "우건 닉네임",
                            userName = "우건 실제 네임",
                            profilePath = ""
                        ),
                        content = "재밌어요",
                        rating = 9.0
                    ),

                    ReviewInfo(
                        authorInfo = Author(
                            name = "우건 닉네임",
                            userName = "우건 실제 네임",
                            profilePath = ""
                        ),
                        content = "재밌어요",
                        rating = 9.0
                    ),
                ),
                totalPage = 0
            ),
            isShowPosterImage = false,
            onCollapsed = {},
            onExpanded = {},
            onBackEvent = {}
        )
    }
}
