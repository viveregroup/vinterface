package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.viverecollection.jetinferface.data.BaseOption
import com.viverecollection.jetinferface.R

/**
 * Created by Annas Surdyanto on 30/05/22.
 *
 */

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun SelectableTextField(
    modifier: Modifier = Modifier,
    label: String,
    isInvalid: Boolean = false,
    searchHint: String = stringResource(id = R.string.search),
    selection: MutableState<BaseOption>,
    options: List<BaseOption> = emptyList(),
    invalidState: MutableState<Boolean> = mutableStateOf(false),
    optionTitleStyle: TextStyle = MaterialTheme.typography.body2,
    optionDescriptionStyle: TextStyle = MaterialTheme.typography.caption,
    icon: ImageVector = Icons.Default.ArrowDropDown,
    errorIcon: ImageVector = Icons.Filled.Error,
    iconTint: Color = MaterialTheme.colors.secondaryVariant,
    isMandatory: Boolean = false,
    useBorderStroke: Boolean = true
) {
    val isShowOption = remember { mutableStateOf(false) }
    val value = remember { mutableStateOf(selection.value.name) }
    val onSelected: (BaseOption) -> Unit = { selected ->
        selection.value = selected
        value.value = selected.name
    }

    Box(modifier = modifier) {
        if (useBorderStroke) BorderedClickableTextField(
            modifier = modifier,
            label = label,
            state = value,
            isInvalid = isInvalid,
            isMandatory = isMandatory,
            invalidState = invalidState,
            icon = icon,
            errorIcon = errorIcon,
            iconTint = iconTint
        )
        else ClickableTextField(
            modifier = modifier,
            label = label,
            state = value,
            isInvalid = isInvalid,
            isMandatory = isMandatory,
            invalidState = invalidState,
            icon = icon,
            errorIcon = errorIcon,
            iconTint = iconTint
        )
        Box(modifier = Modifier
            .matchParentSize()
            .clickable { isShowOption.value = true }
        )
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
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun <T> CustomSelectableTextField(
    modifier: Modifier = Modifier,
    label: String,
    isInvalid: Boolean = false,
    searchHint: String = stringResource(id = R.string.search),
    selection: MutableState<T>,
    options: List<T> = emptyList(),
    invalidState: MutableState<Boolean> = mutableStateOf(false),
    optionTitleStyle: TextStyle = MaterialTheme.typography.body2,
    optionDescriptionStyle: TextStyle = MaterialTheme.typography.caption,
    onFiltered: ((String) -> List<T>)? = null,
    title: (T) -> String,
    icon: ImageVector = Icons.Default.ArrowDropDown,
    errorIcon: ImageVector = Icons.Filled.Error,
    iconTint: Color = MaterialTheme.colors.secondaryVariant,
    isMandatory: Boolean = false,
    useBorderStroke: Boolean = true
) {
    val isShowOption = remember { mutableStateOf(false) }
    val value = remember { mutableStateOf(title(selection.value)) }
    val onSelected: (T) -> Unit = { selected ->
        selection.value = selected
        value.value = title(selected)
    }

    Box(modifier = modifier) {
        if (useBorderStroke) BorderedClickableTextField(
            modifier = modifier,
            label = label,
            state = value,
            isInvalid = isInvalid,
            isMandatory = isMandatory,
            invalidState = invalidState,
            icon = icon,
            errorIcon = errorIcon,
            iconTint = iconTint
        )
        else ClickableTextField(
            modifier = modifier,
            label = label,
            state = value,
            isInvalid = isInvalid,
            isMandatory = isMandatory,
            invalidState = invalidState,
            icon = icon,
            errorIcon = errorIcon,
            iconTint = iconTint
        )
        Box(modifier = Modifier
            .matchParentSize()
            .clickable { isShowOption.value = true }
        )
    }

    if (isShowOption.value) {
        CustomMultiselectDialogSheet(
            label = label,
            list = options,
            searchHint = searchHint,
            onSelectedChanged = onSelected,
            isShowDialog = isShowOption,
            onFiltered = onFiltered,
            optionContent = {
                CustomOptionItemView(
                    title = title(it),
                    optionTitleStyle = optionTitleStyle,
                    optionDescriptionStyle = optionDescriptionStyle
                )
            }
        )
    }
}