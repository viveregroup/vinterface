package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by Annas Surdyanto on 09/12/21.
 *
 */

@Composable
fun HeadingText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.h6,
    paddingTop: Dp = 8.dp,
    paddingBottom: Dp = 8.dp
) {
    Text(
        text = text,
        modifier = modifier.padding(bottom = paddingTop, top = paddingBottom),
        style = style
    )
}

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.subtitle1,
    paddingTop: Dp = 8.dp,
    paddingBottom: Dp = 8.dp
) {
    Text(
        text = text,
        modifier = modifier.padding(bottom = paddingTop, top = paddingBottom),
        style = style
    )
}

@Composable
fun BodyTextBold(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    style: TextStyle = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold),
    maxLines: Int = 1000,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        style = style,
        modifier = modifier,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow
    )
}

@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    style: TextStyle = MaterialTheme.typography.body1,
    maxLines: Int = 1000,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        style = style,
        modifier = modifier,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow
    )
}

@Composable
fun CaptionTextBlack(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.onPrimary)
) {
    Text(text = text, style = style, modifier = modifier)
}

@Composable
fun CaptionText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.caption,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(text = text, style = style, modifier = modifier, textAlign = textAlign)
}

@Composable
fun BodyTextSecondary(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.secondary)
) {
    Text(
        text = text,
        modifier = modifier,
        style = style
    )
}