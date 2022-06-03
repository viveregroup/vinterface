package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * Created by Annas Surdyanto on 28/12/21.
 *
 */


@Composable
fun AppBrandBar(
    drawableId: Int,
    onTrailingIconClicked: (() -> Unit)? = null,
    trailingIcon: ImageVector? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary)
    ) {
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = "bar icon",
            modifier = Modifier
                .align(Alignment.Center)
                .padding(keyLine2)
                .height(50.dp),
            contentScale = ContentScale.Fit
        )
        trailingIcon?.let { icon ->
            Icon(
                imageVector = icon,
                contentDescription = "trailing icon of app brand bar",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = keyLine2)
                    .clickable { onTrailingIconClicked?.let { it() } }
            )
        }
    }
}