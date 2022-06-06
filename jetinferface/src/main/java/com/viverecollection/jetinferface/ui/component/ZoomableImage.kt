package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.skydoves.landscapist.glide.GlideImage
import com.viverecollection.jetinferface.R

@Composable
fun ClickableImage(
    modifier: Modifier = Modifier,
    imageUrl: String = "",
    title: String? = null,
    description: String? = null,
    contentScale: ContentScale = ContentScale.FillBounds,
    contentDescription: String? = null,
    dismissMessage: String = stringResource(id = R.string.dismiss),
    painter: Painter
) {
    val showImage = remember { mutableStateOf(false) }
    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier.clickable { showImage.value = true }
    )

    if (showImage.value && imageUrl.isNotEmpty()) ZoomableImage(
        image = imageUrl,
        isShowing = showImage,
        description = description,
        title = title,
        dismissMessage = dismissMessage
    )
}

@Composable
fun ZoomableImage(
    image: Any,
    title: String? = null,
    description: String? = null,
    dismissMessage: String = stringResource(id = R.string.dismiss),
    isShowing: MutableState<Boolean>
) {

    // set up all transformation states
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        rotation += rotationChange
        offset += offsetChange
    }

    if (isShowing.value) {
        Dialog(
            onDismissRequest = { isShowing.value = false },
            content = {
                Box(modifier = Modifier.padding()) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .background(
                                MaterialTheme.colors.surface,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .fillMaxWidth()
                            .padding(sixteenDp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        title?.let {
                            BodyTextBold(
                                text = it,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                            Spacer()
                        }
                        GlideImage(
                            imageModel = image,
                            modifier = Modifier
                                .size(300.dp)
                                .transformable(state = state)
                                .graphicsLayer(
                                    scaleX = scale,
                                    scaleY = scale,
                                    rotationZ = rotation,
                                    translationX = offset.x,
                                    translationY = offset.y
                                ),
                            contentScale = ContentScale.Fit,
                            contentDescription = null
                        )
                        description?.let {
                            BodyText(
                                text = it,
                                modifier = Modifier.fillMaxWidth(),
                                style = MaterialTheme.typography.caption.copy(Color.DarkGray),
                                textAlign = TextAlign.Justify
                            )
                        }
                        Spacer()
                        PrimaryButton(text = dismissMessage, onClick = {
                            isShowing.value = false
                        })
                    }
                }
            })
    }
}