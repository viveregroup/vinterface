package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by Annas Surdyanto on 06/01/22.
 *
 */

@Composable
fun CardView(
    modifier: Modifier = Modifier,
    elevation: Dp = 3.dp,
    shape: Shape = RoundedCornerShape(fourDp),
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = elevation,
        content = content,
        shape = shape
    )
}

@Composable
fun BorderedCardView(
    modifier: Modifier = Modifier,
    borderWidth: Dp = 1.dp,
    borderColor: Color = MaterialTheme.colors.secondary,
    shape: Shape = RoundedCornerShape(10.dp),
    elevation: Dp = 3.dp,
    background: Color = MaterialTheme.colors.surface,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .border(borderWidth, borderColor, shape = shape)
            .background(background),
        elevation = elevation,
        content = content
    )
}