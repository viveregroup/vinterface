package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstraintLayout

/**
 * Created by Annas Surdyanto on 11/01/22.
 *
 */

@Composable
fun ExpandableHeading(
    modifier: Modifier = Modifier,
    label: String,
    defaultState: Boolean = true,
    arrowColor: Color = Color.Gray,
    contentMargin: Dp = keyLine2,
    headingStyle: TextStyle = MaterialTheme.typography.h6,
    content: @Composable () -> Unit,
) {
    val showingState = remember { mutableStateOf(defaultState) }
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (textRef, iconRef, contentRef) = createRefs()
        HeadingText(
            text = label,
            modifier = Modifier
                .constrainAs(textRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .clickable { showingState.value = !showingState.value },
            style = headingStyle
        )
        val contactArrow =
            if (showingState.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown

        Icon(
            imageVector = contactArrow,
            contentDescription = "icon trailing",
            tint = arrowColor,
            modifier = Modifier
                .constrainAs(iconRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(textRef.bottom)
                    end.linkTo(parent.end)
                }
                .clickable { showingState.value = !showingState.value }
        )

        if (showingState.value) {
            Column(modifier = Modifier
                .constrainAs(contentRef) {
                    top.linkTo(textRef.bottom, margin = contentMargin)
                }) {
                content()
            }
        }
    }
}

@Composable
fun ExpandableTitle(
    modifier: Modifier = Modifier,
    label: String,
    description: String? = null,
    defaultState: Boolean = true,
    arrowColor: Color = Color.Gray,
    contentMargin: Dp = keyLine2,
    headingStyle: TextStyle = MaterialTheme.typography.subtitle1,
    descriptionStyle: TextStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.secondary),
    content: @Composable () -> Unit,
) {
    val showingState = remember { mutableStateOf(defaultState) }
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (textRef, descRef, iconRef, contentRef) = createRefs()
        TitleText(
            text = label,
            modifier = Modifier
                .constrainAs(textRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .clickable { showingState.value = !showingState.value },
            style = headingStyle
        )

        description?.let {
            Text(text = it,
                style = descriptionStyle,
                modifier = Modifier.constrainAs(descRef) {
                    top.linkTo(parent.top)
                    end.linkTo(iconRef.start, margin = keyLine1)
                    bottom.linkTo(textRef.bottom)
                })
        }
        val contactArrow =
            if (showingState.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
        Icon(
            imageVector = contactArrow,
            contentDescription = "icon trailing",
            tint = arrowColor,
            modifier = Modifier
                .constrainAs(iconRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(textRef.bottom)
                    end.linkTo(parent.end)
                }
                .clickable { showingState.value = !showingState.value }
        )

        if (showingState.value) {
            Column(modifier = Modifier
                .constrainAs(contentRef) {
                    top.linkTo(textRef.bottom, margin = contentMargin)
                }) {
                content()
            }
        }
    }
}

@Composable
fun ExpandableLabel(
    modifier: Modifier = Modifier,
    label: String,
    description: String? = null,
    defaultState: Boolean = true,
    arrowColor: Color = Color.Gray,
    contentMargin: Dp = keyLine2,
    headingStyle: TextStyle = MaterialTheme.typography.body2,
    descriptionStyle: TextStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.secondary),
    content: @Composable () -> Unit,
) {
    val showingState = remember { mutableStateOf(defaultState) }
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (textRef, descRef, iconRef, contentRef) = createRefs()
        val style = MaterialTheme.typography.body2
        BodyText(
            text = label,
            modifier = Modifier
                .constrainAs(textRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .clickable { showingState.value = !showingState.value },
            style = headingStyle
        )

        description?.let {
            Text(text = it,
                style = descriptionStyle,
                modifier = Modifier.constrainAs(descRef) {
                    top.linkTo(parent.top)
                    end.linkTo(iconRef.start, margin = keyLine1)
                    bottom.linkTo(textRef.bottom)
                })
        }
        val contactArrow =
            if (showingState.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
        Icon(
            imageVector = contactArrow,
            contentDescription = "icon trailing",
            tint = arrowColor,
            modifier = Modifier
                .constrainAs(iconRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(textRef.bottom)
                    end.linkTo(parent.end)
                }
                .clickable { showingState.value = !showingState.value }
        )

        if (showingState.value) {
            Column(modifier = Modifier
                .constrainAs(contentRef) {
                    top.linkTo(textRef.bottom, margin = contentMargin)
                }) {
                content()
            }
        }
    }
}