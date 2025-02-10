package com.lwg.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lwg.designsystem.R
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.designsystem.theme.LwgTypo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LwgTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    topBarIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = LwgTypo.typography.titleLargeR
            )
        },
        navigationIcon = {
            topBarIcon()
        },
        actions = actions,
        colors = colors
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LwgCenterAlignedTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    topBarIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = LwgTypo.typography.titleLargeR
            )
        },
        navigationIcon = {
            topBarIcon()
        },
        actions = actions,
        colors = colors
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LwgCollapsingImageTopAppBar(
    title: String,
    backgroundImagePath: String,
    imageHeight: Dp = 300.dp,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    topBarIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    LargeTopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LwgImage(
                    modifier = Modifier
                        .fillMaxWidth(),
                    imageUrl = backgroundImagePath
                )
            }

            Text(
                text = title,
                style = LwgTypo.typography.titleLargeR
            )
        },
        navigationIcon = {
            topBarIcon()
        },
        actions = actions,
        colors = colors,
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun LwgTopAppBarPreview() {
    LwgTheme {
        LwgTopAppBar(
            title = "Preview",
            topBarIcon = {
                Box(
                    modifier = Modifier
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun LwgCenterAlignedTopAppBarPreview() {
    LwgTheme {
        LwgCenterAlignedTopAppBar(
            title = "Preview",
            topBarIcon = {
                Box(
                    modifier = Modifier
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            actions = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    LwgIcon(
                        modifier = Modifier,
                        iconType = IconType.Default(R.drawable.ic_favorite_color)
                    )
                    LwgIcon(
                        modifier = Modifier,
                        iconType = IconType.Default(R.drawable.ic_favorite_color)
                    )
                    LwgIcon(
                        modifier = Modifier,
                        iconType = IconType.Default(R.drawable.ic_favorite_color)
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun LwgCollapsingImageTopAppBarPreview() {
    LwgTheme {
        LwgCollapsingImageTopAppBar(
            title = "Preview",
            backgroundImagePath = "https://fastly.picsum.photos/id/471/200/200.jpg?hmac=LEJyaxVwJ-Df2QN6POR3mvD0nKLbC6GIntpAUjTR3gM"
        )
    }
}