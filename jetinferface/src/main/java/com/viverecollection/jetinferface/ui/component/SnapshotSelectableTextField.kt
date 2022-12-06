package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.viverecollection.jetinferface.R
import com.viverecollection.jetinferface.util.toashShortly
import kotlinx.coroutines.launch

/**
 * Created by Annas Surdyanto on 18/11/22.
 *
 */
@Composable
fun <T> SnapshotListTextField(
    modifier: Modifier = Modifier,
    selectionColor: Color = MaterialTheme.colors.primary,
    selectionShape: Shape = RoundedCornerShape(10.dp),
    selectedTitleStyle: TextStyle = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.surface),
    selectedDescriptionStyle: TextStyle = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.surface),
    optionTitleStyle: TextStyle = MaterialTheme.typography.body2,
    optionDescriptionStyle: TextStyle = MaterialTheme.typography.caption,
    label: String,
    isInvalid: Boolean = false,
    selections: SnapshotStateList<T>,
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
    borderColor: Color = Color.Gray,
    borderShape: Shape = RoundedCornerShape(5.dp),
    title: (T) -> String,
    description: ((T) -> String)? = null,
    useColumnScopeArrangement: Boolean = true,
) {
    val onDelete: (T) -> Unit = {
        selections.remove(it)
    }
    CustomSnapshotListTextField(
        modifier = modifier,
        label = label,
        searchHint = searchHint,
        isInvalid = isInvalid,
        selections = selections,
        options = options,
        borderColor = borderColor,
        borderShape = borderShape,
        maxSelection = maxSelection,
        optionContent = {
            CustomOptionItemView(
                title = title(it),
                description = description?.invoke(it) ?: "",
                optionTitleStyle = optionTitleStyle,
                optionDescriptionStyle = optionDescriptionStyle
            )
        },
        warningMax = warningMax,
        invalidState = invalidState,
        placeholderStyle = placeholderStyle,
        icon = icon,
        errorIcon = errorIcon,
        iconTint = iconTint,
        isMandatory = isMandatory,
        onFiltered = onFiltered
    ) {
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
                        description = { description?.invoke(it) ?: "" },
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
                        description = { description?.invoke(it) ?: "" },
                        onDelete = onDelete,
                        titleStyle = selectedTitleStyle,
                        descriptionStyle = selectedDescriptionStyle
                    )
                }
            )
        }
    }
}


@Composable
fun <T> CustomSelectedColumnScopeListView(
    modifier: Modifier = Modifier,
    selections: SnapshotStateList<T>,
    itemContent: @Composable (T) -> Unit,
) {
    if (selections.isNotEmpty()) {
        Column(
            modifier = modifier.padding(bottom = sixDp),
            verticalArrangement = Arrangement.spacedBy(fourDp)
        ) {
            selections.forEach {
                itemContent(it)
            }
        }
    }
}

@Composable
fun <T> CustomSelectedRowScopeListView(
    modifier: Modifier = Modifier,
    selections: SnapshotStateList<T>,
    itemContent: @Composable (T) -> Unit,
) {
    if (selections.isNotEmpty()) {
        LazyRow(
            modifier = modifier.padding(bottom = sixDp),
            horizontalArrangement = Arrangement.spacedBy(fourDp)
        ) {
            items(selections) {
                itemContent(it)
            }
        }
    }
}


@Composable
fun <T> CustomSnapshotListTextField(
    modifier: Modifier = Modifier,
    label: String,
    searchHint: String = stringResource(id = R.string.search),
    isInvalid: Boolean = false,
    selections: SnapshotStateList<T>,
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
    borderColor: Color = MaterialTheme.colors.secondary,
    borderShape: Shape = RoundedCornerShape(3.dp),
    onFiltered: ((String) -> List<T>)? = null,
    selectedContent: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val isShowOption = remember { mutableStateOf(false) }
    val context = LocalContext.current

    val onSelected: (T) -> Unit = { selection ->
        scope.launch {
            if (selections.size < maxSelection) {
                val isExist = selections.find { it == selection } != null
                if (!isExist) selections.add(selection)
            } else warningMax.toashShortly(context)
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

    Column(
        modifier = modifier
            .border(1.dp, borderColor, borderShape)
            .padding(horizontal = eightDp)
    ) {
        Box(modifier = Modifier) {
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .padding(horizontal = eightDp)
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
    }
}