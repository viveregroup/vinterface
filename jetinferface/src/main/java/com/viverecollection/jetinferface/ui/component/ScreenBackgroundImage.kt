package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.viverecollection.jetinferface.R

/**
 * Created by Annas Surdyanto on 10/06/22.
 *
 */
@Composable
fun ScreenBackgroundImage(painter: Painter, scale: ContentScale = ContentScale.FillBounds) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painter,
        contentDescription = stringResource(R.string.cd_background_image),
        contentScale = scale
    )
}