package com.viverecollection.jetinferface.ui.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by Annas Surdyanto on 18/11/21.
 *
 */

@Composable
fun ScreenContainer(
    modifier: Modifier = Modifier,
    appBarBackground: Color = MaterialTheme.colors.primary,
    appBarContentColor: Color = MaterialTheme.colors.onPrimary,
    barTitle: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onLeadingIconClicked: (() -> Unit)? = null,
    onTrailingIconClicked: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            barTitle?.let {
                AppBar(
                    barTitle = barTitle,
                    appBarBackground = appBarBackground,
                    appBarContentColor = appBarContentColor,
                    leadingIcon = leadingIcon,
                    endTrailingIcon = trailingIcon,
                    onLeadingIconClicked = onLeadingIconClicked,
                    onEndTrailingIconClicked = onTrailingIconClicked
                )
            }
        },
        content = { content() }
    )
}