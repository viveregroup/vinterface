package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.viverecollection.jetinferface.R


/**
 * Created by Annas Surdyanto on 11/01/22.
 *
 */
@Composable
fun DataDescriptor(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    valueWidth: Float = 7f,
    labelStyle: TextStyle = MaterialTheme.typography.body2.copy(color = Color.DarkGray),
    valueStyle: TextStyle = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.SemiBold)
) {
    val semiColonWidth = 0.2f
    val labelWidth = 10f - valueWidth + semiColonWidth
    Row(modifier = modifier) {
        BodyText(
            text = label,
            modifier = Modifier.weight(labelWidth),
            style = labelStyle
        )
        BodyText(
            text = ": ",
            modifier = Modifier.weight(semiColonWidth),
            style = labelStyle
        )
        BodyTextBold(
            text = value,
            modifier = Modifier
                .weight(valueWidth)
                .padding(bottom = 4.dp),
            style = valueStyle
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PhotoDescription(label: String, url: String, caption: String = "", valueWidth: Float = 8f) {
    val semiColonWidth = 0.2f
    val labelWidth = 10f - valueWidth + semiColonWidth

    Row {
        val style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.secondary)
        BodyText(text = label, modifier = Modifier.weight(labelWidth), style = style)
        BodyText(text = ": ", modifier = Modifier.weight(semiColonWidth), style = style)
        Column(modifier = Modifier.weight(valueWidth)) {
            val painter =
                if (url.isNotEmpty()) rememberImagePainter(data = url) else painterResource(
                    id = R.drawable.default_image
                )
            ClickableImage(
                painter = painter,
                contentDescription = "image showing",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(100.dp),
                imageUrl = url
            )
            if (caption.isNotEmpty()) CaptionText(text = caption)
        }
    }
}