package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Created by Annas Surdyanto on 01/10/21.
 *
 */

@Composable
fun AppBar(
    appBarBackground: Color = MaterialTheme.colors.primary,
    appBarContentColor: Color = MaterialTheme.colors.onPrimary,
    barTitle: String,
    leadingIcon: ImageVector? = null,
    endTrailingIcon: ImageVector? = null,
    startTrailingIcon: ImageVector? = null,
    onLeadingIconClicked: (() -> Unit)? = null,
    onStartTrailingIconClicked: (() -> Unit)? = null,
    onEndTrailingIconClicked: (() -> Unit)? = null,
) {
    TopAppBar(
        backgroundColor = appBarBackground,
        contentColor = appBarContentColor,
        elevation = 0.dp,
        modifier = Modifier
            .background(color = appBarBackground)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        leadingIcon?.let {
            Icon(
                imageVector = it,
                contentDescription = "leading icon of $barTitle",
                tint = MaterialTheme.colors.onPrimary,
                modifier = Modifier.clickable { onLeadingIconClicked?.let { clicked -> clicked() } }
            )
            Spacer(modifier = Modifier.size(keyLine2))
        }
        Text(
            modifier = Modifier,
            text = barTitle,
            style = MaterialTheme.typography.body1.copy(
                color = appBarContentColor,
                fontWeight = FontWeight.SemiBold
            )
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.align(Alignment.End)) {
                startTrailingIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = it.toString(),
                        tint = MaterialTheme.colors.onPrimary,
                        modifier = Modifier
                            .clickable { onStartTrailingIconClicked?.let { clicked -> clicked() } }
                    )
                }
                endTrailingIcon?.let {
                    Spacer()
                    Icon(
                        imageVector = it,
                        contentDescription = it.toString(),
                        tint = MaterialTheme.colors.onPrimary,
                        modifier = Modifier
                            .clickable { onEndTrailingIconClicked?.let { clicked -> clicked() } }
                    )
                }
            }
        }
    }

}