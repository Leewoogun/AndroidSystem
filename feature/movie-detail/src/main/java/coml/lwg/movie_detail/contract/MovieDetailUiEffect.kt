package coml.lwg.movie_detail.contract

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
internal sealed interface MovieDetailUiEffect {
    @Immutable
    data class ShowSnackBar(val message: String) : MovieDetailUiEffect
}