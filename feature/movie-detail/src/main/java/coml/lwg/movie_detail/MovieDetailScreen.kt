package coml.lwg.movie_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.component.FlexibleTopBar
import com.lwg.designsystem.component.LwgImage
import coml.lwg.movie_detail.contract.MovieDetailUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MovieDetailScreen(
    movieDetailUiState: MovieDetailUiState.MovieDetailData
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            FlexibleTopBar(
                scrollBehavior = scrollBehavior,
                content = {
                    Box {
                        LwgImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            imageUrl = movieDetailUiState.movieDetail.posterImageUrl
                        )
                    }
                }
            )

            TopAppBar(
                title = {
                    Text(text = "I'm title")
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = innerPadding,
        ) {
            items(50) { index ->
                Text("Item $index", modifier = Modifier.padding(16.dp))
            }
        }
    }
}
