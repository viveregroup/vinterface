package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.viverecollection.jetinferface.R

/**
 * Created by Annas Surdyanto on 30/05/22.
 *
 */
@OptIn(ExperimentalCoilApi::class)
@Composable
fun CircleImageView(
    modifier: Modifier = Modifier,
    size: Dp = 100.dp,
    shape: Shape = CircleShape,
    imageUrl: String,
    title: String = "",
    description: String = "",
    isClickable: Boolean = false,
    contentScale: ContentScale = ContentScale.Crop
) {
    val showImage = remember { mutableStateOf(false) }
    if (isClickable) Image(
        painter = if (imageUrl.isEmpty()) painterResource(id = R.drawable.default_image)
        else rememberImagePainter(data = imageUrl),
        contentDescription = "profilePhoto",
        modifier = modifier
            .clip(shape = shape)
            .size(size)
            .clickable { showImage.value = true },
        contentScale = contentScale
    )
    else Image(
        painter = if (imageUrl.isEmpty()) painterResource(id = R.drawable.default_image)
        else rememberImagePainter(data = imageUrl),
        contentDescription = "profilePhoto",
        modifier = modifier
            .clip(shape = shape)
            .size(size),
        contentScale = ContentScale.Crop
    )

    if (showImage.value && imageUrl.isNotEmpty()) ZoomableImage(
        image = imageUrl,
        isShowing = showImage,
        description = description,
        title = title
    )
}

@Composable
@Preview
fun CircleImageViewPreview() = CircleImageView(
    title = stringResource(id = R.string.long_title_sample),
    description = stringResource(id = R.string.long_description_sample),
    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkFtOJDNY1RfJkFWrpFZPzjROR10yyb1-Ohw&usqp=CAU"
)