package coml.lwg.movie_detail.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.theme.BlackA20
import com.lwg.designsystem.theme.Gray1
import com.lwg.designsystem.theme.Yellow
import kotlin.math.floor

@Composable
fun StarRatingBar(
    rating: Double, // 0.0 ~ 10.0 사이의 실수형 별점
) {
    val maxStars = 5 // 별 개수는 5개로 고정
    val normalizedRating = (rating / 2.0).coerceIn(0.0, maxStars.toDouble()) // 10점 만점을 5점 기준으로 변환
    val density = LocalDensity.current.density
    val starSize = (12f * density).dp
    val starSpacing = (1f * density).dp

    val fullStars = floor(normalizedRating).toInt()  // 꽉 찬 별 개수
    val hasHalfStar = (normalizedRating - fullStars) >= 0.5  // 반개 별 여부
    val emptyStars = maxStars - (fullStars + if (hasHalfStar) 1 else 0) // 빈 별 개수

    Row(
        modifier = Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 꽉 찬 별
        repeat(fullStars) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Yellow,
                modifier = Modifier.size(starSize)
            )
            Spacer(modifier = Modifier.width(starSpacing))
        }

        // 반개 별
        if (hasHalfStar) {
            Box(
                modifier = Modifier
                    .size(starSize)
                    .clip(RoundedCornerShape(4.dp))
            ) {
                // 빈 별 배경
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = BlackA20,
                    modifier = Modifier.matchParentSize()
                )
                // 절반만 칠한 별
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Yellow,
                    modifier = Modifier
                        .matchParentSize()
                        .drawWithContent {
                            clipRect(right = size.width / 2) {
                                this@drawWithContent.drawContent()
                            }
                        }
                )
            }
            Spacer(modifier = Modifier.width(starSpacing))
        }

        // 빈 별
        repeat(emptyStars) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Gray1,
                modifier = Modifier.size(starSize)
            )
            if (it < emptyStars - 1) {
                Spacer(modifier = Modifier.width(starSpacing))
            }
        }
    }
}


