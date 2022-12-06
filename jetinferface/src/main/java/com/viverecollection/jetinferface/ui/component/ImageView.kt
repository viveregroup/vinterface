package com.viverecollection.jetinferface.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.skydoves.landscapist.glide.GlideImage
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
fun ImageSheetItem(image: String) {
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        rotation += rotationChange
        offset += offsetChange
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = eightDp),
        elevation = twoDp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(modifier = Modifier.padding(eightDp)) {
            GlideImage(
                imageModel = image,
                modifier = Modifier
                    .fillMaxSize()
                    .transformable(state = state)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        rotationZ = rotation,
                        translationX = offset.x,
                        translationY = offset.y
                    ),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun DownloadableImageView(
    fileName: String, url: String,
    onDownload: (String, String) -> Unit,
    onDismiss: () -> Unit
) {
    var scale by mutableStateOf(1f)
    var offset by mutableStateOf(Offset.Zero)
    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        scale *= zoomChange
        offset += offsetChange
    }

    val resetScale: () -> Unit = {
        scale = 1f
        offset = Offset.Zero
    }
    val dismiss: () -> Unit = {
        onDismiss()
        resetScale()
    }

    val download: () -> Unit = {
        onDownload(fileName, url)
        resetScale()
    }

    Scaffold(
        topBar = {
            AppBar(
                barTitle = fileName,
                endTrailingIcon = Icons.Default.Download,
                onEndTrailingIconClicked = download,
                appBarBackground = Color.Black,
                appBarContentColor = Color.White,
                leadingIcon = Icons.Default.ArrowBack,
                onLeadingIconClicked = dismiss
            )
        },
        backgroundColor = Color.Black
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            GlideImage(
                imageModel = url,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .transformable(state = state)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    ),
                contentScale = ContentScale.Fit,
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview
fun CircleImageViewPreview() = CircleImageView(
    title = stringResource(id = R.string.long_title_sample),
    description = stringResource(id = R.string.long_description_sample),
    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkFtOJDNY1RfJkFWrpFZPzjROR10yyb1-Ohw&usqp=CAU"
)