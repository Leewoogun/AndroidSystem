package coml.lwg.movie_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.lwg.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val movieId = savedStateHandle.toRoute<Route.Detail>().movieId
}