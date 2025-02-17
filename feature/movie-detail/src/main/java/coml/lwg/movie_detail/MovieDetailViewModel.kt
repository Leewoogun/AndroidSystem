package coml.lwg.movie_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.lwg.data_api.MovieDetailRepository
import com.lwg.model.movie.Review
import com.lwg.navigation.Route
import coml.lwg.movie_detail.contract.MovieDetailUiEffect
import coml.lwg.movie_detail.contract.MovieDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    movieDetailRepository: MovieDetailRepository
): ViewModel() {
    private val movieId = savedStateHandle.toRoute<Route.Detail>().movieId

    val movieDetailUiState: StateFlow<MovieDetailUiState> = movieDetailRepository.getMovieDetail(
        movieId = movieId,
        onError = ::onShowSnackBar
    ).map {
       MovieDetailUiState.MovieDetailData(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MovieDetailUiState.Loading
    )

    val reviewData: StateFlow<Review> = movieDetailRepository.getMovieReviews(
        page = 1,
        movieId = movieId,
        onError = ::onShowSnackBar
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = Review(
            page = 0,
            reviewData = emptyList(),
            totalPage = 0
        )
    )

    private val _movieDetailUiEffect = MutableSharedFlow<MovieDetailUiEffect>()
    val movieDetailUiEffect: SharedFlow<MovieDetailUiEffect> get() = _movieDetailUiEffect

    fun onShowSnackBar(message: String) {
        viewModelScope.launch {
            _movieDetailUiEffect.emit(MovieDetailUiEffect.ShowSnackBar(message = message))
        }
    }
}