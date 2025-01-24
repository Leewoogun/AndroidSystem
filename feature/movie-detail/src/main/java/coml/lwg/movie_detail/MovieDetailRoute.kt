package coml.lwg.movie_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsIgnoringVisibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lwg.designsystem.component.LwgTopAppBar
import com.lwg.designsystem.theme.main

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
internal fun MovieDetailRoute() {
//    Column {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(100.dp)
//                .background(color = main)
//                .windowInsetsPadding(WindowInsets.statusBars)
//        ) {
//            Text(
//                text = "테스트"
//            )
//        }
//    }
    Scaffold(
        topBar = {
            LwgTopAppBar(
                title = "영화 상세",
                colors = TopAppBarDefaults.topAppBarColors(containerColor = main)
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                Text(text = "나는 영화 상세 본문")
            }
        }
    )
}