package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.annotation.ExperimentalCoilApi
import com.viverecollection.jetinferface.R

/**
 * Created by Annas Surdyanto on 30/05/22.
 *
 */

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageDescribedView(
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = MaterialTheme.typography.body1.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp
    ),
    descriptionStyle: TextStyle = MaterialTheme.typography.body1.copy(fontSize = 12.sp),
    imageSize: Dp = 130.dp,
    shape: Shape = RoundedCornerShape(10.dp),
    title: String,
    description: String,
    imageUrl: String,
    maxDescriptionLine: Int = 7,
) {
    ConstraintLayout(modifier = modifier) {
        val (photoRef, titleRef, descriptionRef) = createRefs()
        CircleImageView(
            modifier = Modifier
                .constrainAs(photoRef) {
                    top.linkTo(parent.top)
                }
                .size(imageSize),
            imageUrl = imageUrl,
            size = 130.dp,
            shape = shape,
            isClickable = true
        )

        Text(
            text = title,
            style = titleStyle,
            modifier = Modifier.constrainAs(titleRef) {
                top.linkTo(photoRef.top)
                start.linkTo(photoRef.end, margin = 16.dp)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            textAlign = TextAlign.Start
        )

        Text(
            text = description,
            style = descriptionStyle,
            modifier = Modifier.constrainAs(descriptionRef) {
                top.linkTo(titleRef.bottom, margin = 8.dp)
                start.linkTo(titleRef.start)
                end.linkTo(parent.end)
                bottom.linkTo(photoRef.bottom)
                height = Dimension.fillToConstraints
                width = Dimension.preferredWrapContent
            },
            textAlign = TextAlign.Justify,
            overflow = TextOverflow.Ellipsis,
            maxLines = maxDescriptionLine
        )
    }
}

@Composable
@Preview
fun ImageDescribedPreview() = ImageDescribedView(
    title = stringResource(id = R.string.long_title_sample),
    description = stringResource(id = R.string.long_description_sample),
    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkFtOJDNY1RfJkFWrpFZPzjROR10yyb1-Ohw&usqp=CAU"
)