package com.viverecollection.jetinferface.ui.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

/**
 * Created by Annas Surdyanto on 18/11/21.
 *
 */

@Composable
fun RefreshableScreenContainer(
    modifier: Modifier = Modifier,
    loadingState: Boolean,
    onRefresh: () -> Unit,
    appBarBackground: Color = MaterialTheme.colors.primary,
    appBarContentColor: Color = MaterialTheme.colors.onPrimary,
    barTitle: String? = null,
    leadingIcon: ImageVector? = null,
    startTrailingIcon: ImageVector? = null,
    endTrailingIcon: ImageVector? = null,
    onLeadingIconClicked: (() -> Unit)? = null,
    onStartTrailingIconClicked: (() -> Unit)? = null,
    onEndTrailingIconClicked: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            barTitle?.let {
                AppBar(
                    barTitle = barTitle,
                    appBarBackground = appBarBackground,
                    appBarContentColor = appBarContentColor,
                    leadingIcon = leadingIcon,
                    endTrailingIcon = endTrailingIcon,
                    onLeadingIconClicked = onLeadingIconClicked,
                    onEndTrailingIconClicked = onEndTrailingIconClicked,
                    startTrailingIcon = startTrailingIcon,
                    onStartTrailingIconClicked = onStartTrailingIconClicked
                )
            }
        },
        content = {
            SwipeRefresh(
                state = SwipeRefreshState(loadingState),
                onRefresh = onRefresh,
                modifier = modifier,
                content = content
            )
        }
    )
}

@Composable
fun RefreshableBrandedBarScreenContainer(
    modifier: Modifier = Modifier,
    drawableId: Int,
    loadingState: Boolean,
    onRefresh: () -> Unit,
    onEndTrailingIconClicked: (() -> Unit)? = null,
    trailingIcon: ImageVector? = null,
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppBrandBar(
                onTrailingIconClicked = onEndTrailingIconClicked,
                trailingIcon = trailingIcon,
                drawableId = drawableId
            )
        },
        content = {
            SwipeRefresh(
                state = SwipeRefreshState(loadingState),
                onRefresh = onRefresh,
                modifier = modifier,
                content = content
            )
        }
    )
}