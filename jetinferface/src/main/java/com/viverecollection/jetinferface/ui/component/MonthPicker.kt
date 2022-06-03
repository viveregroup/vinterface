package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import com.viverecollection.jetinferface.data.Month
import com.viverecollection.jetinferface.data.fiveYearsAround
import com.viverecollection.jetinferface.R
import com.viverecollection.jetinferface.util.separateMonthAndYear

/**
 * Created by Annas Surdyanto on 09/02/22.
 *
 */
@Composable
fun MonthPickerView(
    modifier: Modifier = Modifier,
    label: String,
    state: MutableState<String>,
    yearOptions: List<String> = fiveYearsAround(),
    isMandatory: Boolean = false,
    invalidState: MutableState<Boolean> = mutableStateOf(false),
    selectionBackground: Color = colorResource(id = R.color.category_9),
    useBorderStroke: Boolean = true
) {
    val month = state.value.separateMonthAndYear()[0]
    val year = state.value.separateMonthAndYear()[1]
    val isShowOptions = remember { mutableStateOf(false) }
    val monthSelected = remember { mutableStateOf(month) }
    val yearSelected = remember { mutableStateOf(year) }
    Box(modifier = modifier) {
        if (useBorderStroke) BorderedClickableTextField(
            modifier = modifier,
            label = label,
            state = state,
            isInvalid = if (isMandatory) state.value.isEmpty() else false,
            invalidState = invalidState
        )
        else ClickableTextField(
            modifier = modifier,
            label = label,
            state = state,
            isInvalid = if (isMandatory) state.value.isEmpty() else false,
            invalidState = invalidState
        )
        Box(modifier = Modifier
            .matchParentSize()
            .clickable {
                isShowOptions.value = true
            })
    }

    if (isShowOptions.value) {
        Dialog(
            onDismissRequest = { isShowOptions.value = false },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colors.surface,
                            shape = RoundedCornerShape(20.dp)
                        )
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(keyLine3)
                    ) {
                        val (titleRef, monthRef, monthLabelRef, yearLabelRef, valueRef, yearRef, buttonRef) = createRefs()
                        Text(
                            text = label.replace("*", ""),
                            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold),
                            modifier = Modifier.constrainAs(titleRef) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            })

                        BodyText(
                            modifier = Modifier.constrainAs(monthLabelRef) {
                                top.linkTo(titleRef.bottom, margin = 30.dp)
                                end.linkTo(parent.end)
                                start.linkTo(parent.start)
                            },
                            text = stringResource(id = R.string.month)
                        )

                        LazyColumn(modifier = Modifier
                            .constrainAs(monthRef) {
                                top.linkTo(monthLabelRef.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }) {

                            Month.getMonthLine(
                                lineOne = { listOfMonth ->
                                    item {
                                        MonthListView(
                                            list = listOfMonth,
                                            monthSelected = monthSelected,
                                            selectionBackground = selectionBackground
                                        )
                                    }
                                },
                                lineTwo = { listOfMonth ->
                                    item {
                                        MonthListView(
                                            list = listOfMonth,
                                            monthSelected = monthSelected,
                                            selectionBackground = selectionBackground
                                        )
                                    }
                                },
                                lineThree = { listOfMonth ->
                                    item {
                                        MonthListView(
                                            list = listOfMonth,
                                            monthSelected = monthSelected,
                                            selectionBackground = selectionBackground
                                        )
                                    }
                                }
                            )
                        }

                        BodyText(
                            modifier = Modifier.constrainAs(yearLabelRef) {
                                top.linkTo(monthRef.bottom, margin = keyLine3)
                                end.linkTo(parent.end)
                                start.linkTo(parent.start)
                            },
                            text = stringResource(id = R.string.year)
                        )

                        LazyRow(modifier = Modifier.constrainAs(yearRef) {
                            top.linkTo(yearLabelRef.bottom)
                            start.linkTo(parent.start, margin = keyLine3)
                            end.linkTo(parent.end, margin = keyLine3)
                        }) {
                            items(yearOptions) {
                                MonthPickerItem(
                                    item = it,
                                    isSelected = yearSelected.value == it,
                                    onClick = { yearSelected.value = it },
                                    width = 60.dp,
                                    selectionBackground = selectionBackground
                                )
                            }
                        }

                        val value = Month.list.find { it.id == monthSelected.value }?.name
                        BodyTextBold(
                            modifier = Modifier.constrainAs(valueRef) {
                                top.linkTo(yearRef.bottom, margin = keyLine3)
                                end.linkTo(parent.end)
                            },
                            text = "Selection: $value ${yearSelected.value}"
                        )

                        PrimaryButton(
                            text = stringResource(id = R.string.save),
                            onClick = {
                                isShowOptions.value = false
                                state.value = "${monthSelected.value}-${yearSelected.value}"
                            },
                            modifier = Modifier
                                .constrainAs(buttonRef) {
                                    top.linkTo(valueRef.bottom, margin = keyLine3)
                                }
                                .fillMaxWidth()
                        )
                    }
                }
            }
        )
    }
}

@Composable
private fun MonthListView(
    modifier: Modifier = Modifier,
    list: List<Month>,
    monthSelected: MutableState<String>,
    selectionBackground: Color
) {
    LazyRow(modifier = modifier) {
        items(list) {
            MonthPickerItem(
                item = it.id,
                isSelected = monthSelected.value == it.id,
                onClick = { monthSelected.value = it.id },
                width = 60.dp,
                selectionBackground = selectionBackground
            )
        }
    }
}

@Composable
private fun MonthPickerItem(
    item: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    width: Dp,
    selectionBackground: Color
) {
    val style =
        if (isSelected) MaterialTheme.typography.body1.copy(
            color = selectionBackground,
            fontWeight = FontWeight.Bold
        ) else MaterialTheme.typography.body1

    BodyText(
        text = item,
        style = style,
        modifier = Modifier
            .width(width)
            .clickable(onClick = onClick)
            .padding(keyLine2),
        textAlign = TextAlign.Center
    )
}