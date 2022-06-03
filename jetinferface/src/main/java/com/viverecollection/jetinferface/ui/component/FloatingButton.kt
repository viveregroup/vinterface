package com.viverecollection.jetinferface.ui.component

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by Annas Surdyanto on 25/01/22.
 *
 */

@Composable
fun FloatingButton(modifier: Modifier, icon: ImageVector, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "floating_button_$icon",
        )
    }
}