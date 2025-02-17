package coml.lwg.movie_detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.component.IconType
import com.lwg.designsystem.component.LwgBalloon
import com.lwg.designsystem.component.LwgHorizontalSpacer
import com.lwg.designsystem.component.LwgIcon
import com.lwg.designsystem.component.LwgImage
import com.lwg.designsystem.component.LwgVerticalSpacer
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.LwgTypo
import com.lwg.designsystem.theme.Yellow
import com.lwg.home.R
import com.lwg.model.movie.Author
import com.lwg.model.movie.ReviewInfo
import com.lwg.util.Etc.convertDoubleToString

@Composable
fun UserReviewComponent(
    reviewInfo: ReviewInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row {
            reviewInfo.authorInfo.profilePath?.let {
                LwgImage(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    imageUrl = reviewInfo.authorInfo.profileImageUrl
                )
            } ?: run {
                LwgIcon(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    iconType = IconType.Default(R.drawable.ic_profile),
                    iconColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            LwgHorizontalSpacer(10.dp)
            Column {
                Text(
                    text = reviewInfo.authorInfo.userName,
                    style = LwgTypo.typography.titleMediumB,
                    maxLines = 1
                )

                LwgVerticalSpacer(3.dp)

                Text(
                    text = reviewInfo.content,
                    style = LwgTypo.typography.bodyLargeR,
                    maxLines = 3
                )

                LwgVerticalSpacer(3.dp)

                LwgBalloon(
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    balloonContent = {
                        ReviewVoteBalloon(reviewInfo = reviewInfo)
                    },
                    balloonLayout = {
                        Row {
                            Text(
                                text = "별점 보기",
                                style = LwgTypo.typography.bodyLargeR,
                                color = Yellow
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun ReviewVoteBalloon(
    reviewInfo: ReviewInfo
) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        StarRatingBar(
            rating = reviewInfo.rating,
        )

        LwgHorizontalSpacer(10.dp)

        Text(
            text = convertDoubleToString(reviewInfo.rating),
            style = LwgTypo.typography.titleMediumR,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun UserReviewComponentPreview() {
    LwgTheme {
        UserReviewComponent(
            modifier = Modifier
                .fillMaxWidth(),
            reviewInfo = ReviewInfo(
                authorInfo = Author(
                    name = "우건 닉네임",
                    userName = "우건 실제 네임",
                    profilePath = ""
                ),
                content = "댓글 내용",
                rating = 5.0
            ),
        )
    }
}

@Composable
@Preview
private fun ReviewVoteBalloonPreview() {
    LwgTheme {
        ReviewVoteBalloon(
            reviewInfo = ReviewInfo(
                authorInfo = Author(
                    name = "우건 닉네임",
                    userName = "우건 실제 네임",
                    profilePath = ""
                ),
                content = "댓글 내용",
                rating = 5.0
            )
        )
    }
}
