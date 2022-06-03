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
import com.viverecollection.jetinferface.data.BaseOption
import com.viverecollection.jetinferface.data.deleteItem
import com.viverecollection.jetinferface.util.toashShortly
import kotlinx.coroutines.launch

/**
 * Created by Annas Surdyanto on 12/04/22.
 *
 */

/**
 * Customizable multi-selection text field.
 * Use this if you want to custom the content
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MultiselectTextField(
    modifier: Modifier = Modifier,
    label: String,
    isInvalid: Boolean = false,
    selections: MutableState<List<BaseOption>>,
    searchHint: String = stringResource(id = R.string.search),
    optionTitleStyle: TextStyle = MaterialTheme.typography.body2,
    optionDescriptionStyle: TextStyle = MaterialTheme.typography.caption,
    options: List<BaseOption> = emptyList(),
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
    lineColor: Color = MaterialTheme.colors.secondary,
    content: @Composable () -> Unit,
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

    val onSelected: (BaseOption) -> Unit = { selection ->
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
        MultiselectDialogSheet(
            label = label,
            list = options,
            searchHint = searchHint,
            onSelectedChanged = onSelected,
            isShowDialog = isShowOption,
            optionTitleStyle = optionTitleStyle,
            optionDescriptionStyle = optionDescriptionStyle
        )
    }

/*    BottomSheetScaffold(
        modifier = Modifier,
        scaffoldState = bottomSheetState,
        sheetPeekHeight = 56.dp,
        sheetContent = {
            MultiselectSheet(
                label = label,
                list = options,
                onSelectedChanged = onSelected
            )
        })*/
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

        content()
        Spacer(size = keyLine1)
        Divider(color = lineColor, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
    }
}


/**
 * Default multi-selection text field.
 * Use this if you want to have default content view.
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MultiselectTextField(
    modifier: Modifier = Modifier,
    label: String,
    isInvalid: Boolean = false,
    selections: MutableState<List<BaseOption>>,
    selectedTitleStyle: TextStyle = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.surface),
    selectedDescriptionStyle: TextStyle = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.surface),
    optionTitleStyle: TextStyle = MaterialTheme.typography.body2,
    optionDescriptionStyle: TextStyle = MaterialTheme.typography.caption,
    options: List<BaseOption> = emptyList(),
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
    useColumnScopeArrangement: Boolean = true,
    lineColor: Color = MaterialTheme.colors.secondary
) {
    MultiselectTextField(
        modifier = modifier,
        label = label,
        selections = selections,
        isInvalid = isInvalid,
        optionDescriptionStyle = optionDescriptionStyle,
        optionTitleStyle = optionTitleStyle,
        options = options,
        maxSelection = maxSelection,
        warningMax = warningMax,
        invalidState = invalidState,
        placeholderStyle = placeholderStyle,
        icon = icon,
        errorIcon = errorIcon,
        iconTint = iconTint,
        isMandatory = isMandatory,
        lineColor = lineColor
    ) {
        if (useColumnScopeArrangement) {
            DefaultSelectedColumnScope(
                selections = selections,
                selectedDescriptionStyle = selectedDescriptionStyle,
                selectedTitleStyle = selectedTitleStyle
            )
        } else {
            DefaultSelectedRowScope(
                selections = selections,
                selectedDescriptionStyle = selectedDescriptionStyle,
                selectedTitleStyle = selectedTitleStyle
            )
        }
    }
}

@Composable
fun SelectedRowScopeListView(
    modifier: Modifier = Modifier,
    selections: MutableState<List<BaseOption>>,
    content: @Composable (BaseOption, deletItem: (BaseOption) -> Unit) -> Unit,
) {
    val onDelete: (BaseOption) -> Unit = { selection ->
        selections.value.deleteItem(selection) {
            selections.value = it
        }
    }

    LazyRow(
        modifier = modifier.padding(start = keyLine3)
    ) {
        items(selections.value) {
            content(it, onDelete)
        }
    }
}

@Composable
fun SelectedColumnScopeListView(
    modifier: Modifier = Modifier,
    selections: MutableState<List<BaseOption>>,
    content: @Composable (BaseOption, deletItem: (BaseOption) -> Unit) -> Unit,
) {
    val onDelete: (BaseOption) -> Unit = { selection ->
        selections.value.deleteItem(selection) {
            selections.value = it
        }
    }

    Column(
        modifier = modifier.padding(start = keyLine3)
    ) {
        selections.value.forEach {
            content(it, onDelete)
        }
    }
}

@Composable
fun DefaultSelectedColumnScope(
    modifier: Modifier = Modifier,
    selections: MutableState<List<BaseOption>>,
    selectedTitleStyle: TextStyle = MaterialTheme.typography.body2.copy(color = Color.White),
    selectedDescriptionStyle: TextStyle = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.secondary),
    deleteIcon: ImageVector = Icons.Default.DeleteForever,
    deleteIconTint: Color = Color.White,
) {
    val onDelete: (BaseOption) -> Unit = { selection ->
        selections.value.deleteItem(selection) {
            selections.value = it
        }
    }

    SelectedColumnScopeListView(
        modifier = modifier,
        selections = selections
    ) { selectedItem, deleteItem ->
        MultiselectItemView(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.colorAccent),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(keyLine2),
            item = selectedItem,
            onDelete = onDelete,
            titleStyle = selectedTitleStyle,
            descriptionStyle = selectedDescriptionStyle,
            deleteIcon = deleteIcon,
            deleteIconTint = deleteIconTint,
        )
    }
}

@Composable
fun DefaultSelectedRowScope(
    modifier: Modifier = Modifier,
    selections: MutableState<List<BaseOption>>,
    selectedTitleStyle: TextStyle = MaterialTheme.typography.body2.copy(color = Color.White),
    selectedDescriptionStyle: TextStyle = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.secondary),
    deleteIcon: ImageVector = Icons.Default.DeleteForever,
    deleteIconTint: Color = Color.White,
) {
    val onDelete: (BaseOption) -> Unit = { selection ->
        selections.value.deleteItem(selection) {
            selections.value = it
        }
    }

    SelectedRowScopeListView(
        modifier = modifier,
        selections = selections
    ) { selectedItem, deleteItem ->
        MultiselectItemView(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.colorAccent),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(keyLine2),
            item = selectedItem,
            onDelete = onDelete,
            titleStyle = selectedTitleStyle,
            descriptionStyle = selectedDescriptionStyle,
            deleteIcon = deleteIcon,
            deleteIconTint = deleteIconTint,
        )
    }
}

@Composable
fun MultiselectItemView(
    modifier: Modifier = Modifier,
    item: BaseOption,
    titleStyle: TextStyle = MaterialTheme.typography.body2.copy(color = Color.White),
    descriptionStyle: TextStyle = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.secondary),
    onDelete: (BaseOption) -> Unit,
    deleteIcon: ImageVector = Icons.Default.DeleteForever,
    deleteIconTint: Color = Color.White,
) {

    Row(modifier = modifier) {
        if (item.description.isNotEmpty()) {
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = item.name,
                    style = titleStyle
                )
                Text(
                    text = item.description,
                    style = descriptionStyle
                )
            }
        } else Text(
            text = item.name,
            style = titleStyle
        )
        Spacer(size = keyLine1)
        Icon(
            imageVector = deleteIcon,
            contentDescription = "delete selection ${item.id}",
            modifier = Modifier
                .size(15.dp)
                .align(Alignment.CenterVertically)
                .clickable { onDelete(item) },
            tint = deleteIconTint,
        )
    }
    Spacer(size = keyLine0)
}

@Composable
fun MultiselectSheet(
    modifier: Modifier = Modifier,
    label: String,
    list: List<BaseOption>,
    onSelectedChanged: (BaseOption) -> Unit
) {
    val labelToShow = label.replace("*", "")
    val searchKey = remember { mutableStateOf("") }
    val listShowing: List<BaseOption> = if (searchKey.value.isEmpty()) list
    else list.filter {
        it.name.lowercase().contains(searchKey.value.lowercase()) || it.id.lowercase()
            .contains(searchKey.value.lowercase()) || it.description.lowercase()
            .contains(searchKey.value.lowercase())
    }

    Box(modifier = modifier) {
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
                item {
                    SearchView(
                        modifier = Modifier.fillMaxWidth(),
                        state = searchKey,
                        label = labelToShow,
                        style = MaterialTheme.typography.caption,
                        onTextChanged = { }
                    )
                    Spacer()
                }
                listShowing.forEach { option ->
                    item {
                        Card(
                            modifier = Modifier
                                .clickable {
                                    onSelectedChanged(option)
                                }
                                .fillMaxWidth()
                                .border(
                                    1.dp,
                                    Color.LightGray,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .background(MaterialTheme.colors.surface)
                        ) {
                            Column(modifier = Modifier.padding(keyLine12)) {
                                CaptionText(
                                    text = option.id,
                                    modifier = Modifier.wrapContentWidth()
                                )
                                CaptionText(
                                    text = option.name,
                                    modifier = Modifier.wrapContentWidth()
                                )
                            }
                        }
                        Spacer()
                    }
                }
            }
        }

    }
}

@Composable
fun MultiselectDialogSheet(
    label: String,
    list: List<BaseOption>,
    searchHint: String = stringResource(id = R.string.search),
    isShowDialog: MutableState<Boolean>,
    optionTitleStyle: TextStyle = MaterialTheme.typography.body2,
    optionDescriptionStyle: TextStyle = MaterialTheme.typography.caption,
    onSelectedChanged: (BaseOption) -> Unit
) {
    val labelToShow = label.replace("*", "")
    val searchKey = remember { mutableStateOf("") }
    val listShowing: List<BaseOption> = if (searchKey.value.isEmpty()) list
    else list.filter {
        it.name.lowercase().contains(searchKey.value.lowercase()) || it.id.lowercase()
            .contains(searchKey.value.lowercase()) || it.description.lowercase()
            .contains(searchKey.value.lowercase())
    }

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
                            item {
                                SearchView(
                                    modifier = Modifier.fillMaxWidth(),
                                    state = searchKey,
                                    label = searchHint,
                                    style = MaterialTheme.typography.caption,
                                    onTextChanged = { }
                                )
                                Spacer()
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
                                        CustomOptionItemView(
                                            title = option.name,
                                            description = option.description,
                                            optionTitleStyle = optionTitleStyle,
                                            optionDescriptionStyle = optionDescriptionStyle
                                        )
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