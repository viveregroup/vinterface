package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import com.viverecollection.jetinferface.R
import id.co.vivere.util.toashShortly
import kotlinx.coroutines.launch

/**
 * Created by Annas Surdyanto on 12/04/22.
 * Use this for multiplatform project
 */


/**
 * Customizable multi-selection text field with custom data class type.
 * Use this if you want to custom the content.
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T> CustomMultiselectTextField(
    modifier: Modifier = Modifier,
    label: String,
    searchHint: String = stringResource(id = R.string.search),
    isInvalid: Boolean = false,
    selections: MutableState<List<T>>,
    options: List<T> = emptyList(),
    maxSelection: Int = 100,
    optionContent: @Composable (T) -> Unit,
    warningMax: String = stringResource(R.string.selection_maximum_warning),
    invalidState: MutableState<Boolean> = mutableStateOf(false),
    placeholderStyle: TextStyle = MaterialTheme.typography.caption.copy(
        colorResource(id = R.color.colorHint)
    ),
    icon: ImageVector = Icons.Default.ArrowDropDown,
    errorIcon: ImageVector = Icons.Filled.Error,
    iconTint: Color = MaterialTheme.colors.secondaryVariant,
    isMandatory: Boolean = false,
    onFiltered: ((String) -> List<T>)? = null,
    lineColor: Color = MaterialTheme.colors.secondary,
    selectedContent: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val isShowOption = remember { mutableStateOf(false) }
    val bottomSheetState = rememberBottomSheetScaffoldState()
    val context = LocalContext.current
    val toggleSheet: () -> Unit = {
        scope.launch {
            bottomSheetState.bottomSheetState.expand()
        }
    }

    val onSelected: (T) -> Unit = { selection ->
        scope.launch {
            val list = selections.value.toMutableList()
            if (list.size < maxSelection) {
                val isExist = list.find { it == selection } != null
                if (!isExist) {
                    list.add(selection)
                    selections.value = list
                }
            } else warningMax.toashShortly(context)
            //toggleSheet()
        }
    }

    if (isShowOption.value) {
        CustomMultiselectDialogSheet(
            label = label,
            list = options,
            searchHint = searchHint,
            onSelectedChanged = onSelected,
            isShowDialog = isShowOption,
            onFiltered = onFiltered,
            optionContent = optionContent
        )
    }

    Column(modifier = modifier) {
        Box(modifier = Modifier) {
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .padding(horizontal = keyLine12)
            ) {
                Text(
                    text = if (isMandatory) label.plus("*") else label,
                    style = placeholderStyle,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {
                    if (isInvalid && invalidState.value) {
                        Icon(
                            imageVector = errorIcon,
                            contentDescription = "error",
                            modifier = Modifier.align(Alignment.End),
                            tint = iconTint
                        )
                    } else {
                        Icon(
                            imageVector = icon,
                            contentDescription = "icon trailing",
                            modifier = Modifier.align(Alignment.End),
                            tint = iconTint
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable { isShowOption.value = true }
                //.clickable(onClick = toggleSheet)
            )
        }

        selectedContent()
        Spacer(size = keyLine1)
        Divider(color = lineColor, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
    }
}

/**
 * Default custom multi-selection text field with custom data class type.
 * Use this if you want to have default content view.
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T> CustomMultiselectTextField(
    modifier: Modifier = Modifier,
    selectionColor: Color = MaterialTheme.colors.primary,
    selectionShape: Shape = RoundedCornerShape(10.dp),
    selectedTitleStyle: TextStyle = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.surface),
    selectedDescriptionStyle: TextStyle = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.surface),
    optionTitleStyle: TextStyle = MaterialTheme.typography.body2,
    optionDescriptionStyle: TextStyle = MaterialTheme.typography.caption,
    label: String,
    isInvalid: Boolean = false,
    selections: MutableState<List<T>>,
    options: List<T> = emptyList(),
    maxSelection: Int = 100,
    warningMax: String = stringResource(R.string.selection_maximum_warning),
    invalidState: MutableState<Boolean> = mutableStateOf(false),
    placeholderStyle: TextStyle = MaterialTheme.typography.caption.copy(
        colorResource(id = R.color.colorHint)
    ),
    icon: ImageVector = Icons.Default.ArrowDropDown,
    errorIcon: ImageVector = Icons.Filled.Error,
    iconTint: Color = MaterialTheme.colors.secondaryVariant,
    isMandatory: Boolean = false,
    searchHint: String = "",
    onFiltered: ((String) -> List<T>)? = null,
    lineColor: Color = MaterialTheme.colors.secondary,
    title: (T) -> String,
    description: (T) -> String,
    useColumnScopeArrangement: Boolean = true,
    onDelete: ((T) -> Unit)? = null,
) {
    CustomMultiselectTextField(
        modifier = modifier,
        label = label,
        selections = selections,
        isInvalid = isInvalid,
        options = options,
        maxSelection = maxSelection,
        warningMax = warningMax,
        invalidState = invalidState,
        placeholderStyle = placeholderStyle,
        icon = icon,
        searchHint = searchHint,
        onFiltered = onFiltered,
        errorIcon = errorIcon,
        iconTint = iconTint,
        isMandatory = isMandatory,
        lineColor = lineColor,
        optionContent = {
            CustomOptionItemView(
                title = title(it),
                description = description(it),
                optionTitleStyle = optionTitleStyle,
                optionDescriptionStyle = optionDescriptionStyle
            )
        },
        selectedContent = {
            if (useColumnScopeArrangement) {
                CustomSelectedColumnScopeListView(
                    selections = selections,
                    itemContent = {
                        CustomMultiselectItemView(
                            type = it,
                            modifier = Modifier.background(
                                color = selectionColor,
                                shape = selectionShape
                            ),
                            title = title,
                            description = description,
                            onDelete = onDelete,
                            titleStyle = selectedTitleStyle,
                            descriptionStyle = selectedDescriptionStyle
                        )
                    }
                )
            } else {
                CustomSelectedRowScopeListView(
                    selections = selections,
                    itemContent = {
                        CustomMultiselectItemView(
                            type = it,
                            modifier = Modifier.background(
                                color = selectionColor,
                                shape = selectionShape
                            ),
                            title = title,
                            description = description,
                            onDelete = onDelete,
                            titleStyle = selectedTitleStyle,
                            descriptionStyle = selectedDescriptionStyle
                        )
                    }
                )
            }
        }
    )
}


@Composable
fun <T> CustomSelectedColumnScopeListView(
    modifier: Modifier = Modifier,
    selections: MutableState<List<T>>,
    itemContent: @Composable (T) -> Unit,
) {
    if (selections.value.isNotEmpty()) {
        selections.value.forEach {
            Column(modifier = modifier) {
                itemContent(it)
                Spacer(size = keyLine0)
            }
        }
    }
}

@Composable
fun <T> CustomSelectedRowScopeListView(
    modifier: Modifier = Modifier,
    selections: MutableState<List<T>>,
    itemContent: @Composable (T) -> Unit,
) {
    if (selections.value.isNotEmpty()) {
        LazyRow(modifier = modifier) {
            items(selections.value) {
                itemContent(it)
                Spacer(size = keyLine0)
            }
        }
    }
}

@Composable
fun CustomMultiselectItemView(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    titleStyle: TextStyle = MaterialTheme.typography.body2.copy(color = Color.White),
    descriptionStyle: TextStyle = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.secondary),
    onDelete: (() -> Unit)? = null,
    deleteIcon: ImageVector = Icons.Default.DeleteForever,
    deleteIconTint: Color = Color.White,
) {
    val scope = rememberCoroutineScope()
    Row(modifier = modifier.padding(keyLine2)) {
        if (description.isNotEmpty()) {
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = title,
                    style = titleStyle
                )
                Text(
                    text = description,
                    style = descriptionStyle
                )
            }
        } else Text(
            text = title,
            style = titleStyle
        )
        if (onDelete != null) {
            Spacer(size = keyLine1)
            Icon(
                imageVector = deleteIcon,
                contentDescription = "delete selection",
                modifier = Modifier
                    .size(15.dp)
                    .align(Alignment.CenterVertically)
                    .clickable { scope.launch { onDelete() } },
                tint = deleteIconTint,
            )
        }
        Spacer(size = keyLine0)
    }
}

@Composable
fun <T> CustomMultiselectItemView(
    type: T,
    modifier: Modifier = Modifier,
    title: (T) -> String,
    description: (T) -> String,
    titleStyle: TextStyle = MaterialTheme.typography.body2.copy(color = Color.White),
    descriptionStyle: TextStyle = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.secondary),
    onDelete: ((T) -> Unit)? = null,
    deleteIcon: ImageVector = Icons.Default.DeleteForever,
    deleteIconTint: Color = Color.White,
) {
    val scope = rememberCoroutineScope()
    Row(modifier = modifier.padding(keyLine2)) {
        if (description(type).isNotEmpty()) {
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = title(type),
                    style = titleStyle
                )
                Text(
                    text = description(type),
                    style = descriptionStyle
                )
            }
        } else Text(
            text = title(type),
            style = titleStyle
        )
        if (onDelete != null) {
            Spacer(size = keyLine1)
            Icon(
                imageVector = deleteIcon,
                contentDescription = "delete selection",
                modifier = Modifier
                    .size(15.dp)
                    .align(Alignment.CenterVertically)
                    .clickable { scope.launch { onDelete(type) } },
                tint = deleteIconTint,
            )
        }
        Spacer(size = keyLine0)
    }
}

@Composable
fun <T> CustomMultiselectDialogSheet(
    label: String,
    list: List<T>,
    isShowDialog: MutableState<Boolean>,
    searchHint: String = stringResource(id = R.string.search),
    onFiltered: ((String) -> List<T>)? = null,
    optionContent: @Composable (T) -> Unit,
    onSelectedChanged: (T) -> Unit
) {
    val labelToShow = label.replace("*", "")
    val searchKey = remember { mutableStateOf("") }
    val listShowing: List<T> = if (searchKey.value.isEmpty()) list
    else onFiltered!!(searchKey.value)

    if (isShowDialog.value) {
        Dialog(
            onDismissRequest = { isShowDialog.value = false },
            content = {
                Box(modifier = Modifier.fillMaxSize()) {
                    ConstraintLayout(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 500.dp)
                            .background(
                                MaterialTheme.colors.surface,
                                shape = RoundedCornerShape(20.dp)
                            )
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(keyLine3)
                        ) {
                            item {
                                CaptionText(
                                    text = labelToShow,
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                                Spacer()
                            }
                            if (onFiltered != null) {
                                item {
                                    SearchView(
                                        modifier = Modifier.fillMaxWidth(),
                                        state = searchKey,
                                        label = searchHint,
                                        style = MaterialTheme.typography.caption,
                                        onTextChanged = { onFiltered.invoke(it) }
                                    )
                                    Spacer()
                                }
                            }
                            listShowing.forEach { option ->
                                item {
                                    Card(
                                        modifier = Modifier
                                            .clickable {
                                                onSelectedChanged(option)
                                                isShowDialog.value = false
                                            }
                                            .fillMaxWidth()
                                            .border(
                                                1.dp,
                                                Color.LightGray,
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .background(MaterialTheme.colors.surface)
                                    ) {
                                        optionContent(option)
                                    }
                                    Spacer()
                                }
                            }
                        }
                    }
                }
            })
    }
}


@Composable
fun CustomOptionItemView(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    optionTitleStyle: TextStyle = MaterialTheme.typography.body2,
    optionDescriptionStyle: TextStyle = MaterialTheme.typography.caption,
) {
    Column(modifier = modifier.padding(keyLine12)) {
        Text(
            text = title,
            modifier = Modifier.wrapContentWidth(),
            style = optionTitleStyle
        )
        if (description.isNotEmpty()) {
            Text(
                text = description,
                modifier = Modifier.wrapContentWidth(),
                style = optionDescriptionStyle
            )
        }
    }
}