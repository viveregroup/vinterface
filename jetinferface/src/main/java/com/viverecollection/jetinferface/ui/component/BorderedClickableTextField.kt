package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.viverecollection.jetinferface.R

/**
 * Created by Annas Surdyanto on 13/09/21.
 *
 */


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BorderedClickableTextField(
    modifier: Modifier = Modifier,
    label: String,
    state: MutableState<String>? = null,
    staticValue: String? = null,
    focusRequester: FocusRequester? = null,
    inputType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    icon: ImageVector? = null,
    errorIcon: ImageVector = Icons.Filled.Error,
    iconTint: Color = MaterialTheme.colors.secondaryVariant,
    invalidState: MutableState<Boolean> = mutableStateOf(false),
    labelStyle: TextStyle = MaterialTheme.typography.caption,
    placeholderStyle: TextStyle = MaterialTheme.typography.caption.copy(
        colorResource(id = R.color.colorHint)
    ),
    isMandatory: Boolean = false,
    isInvalid: Boolean = false
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(modifier = modifier) {
        OutlinedTextField(
            value = state?.value ?: staticValue ?: "",
            onValueChange = { state?.value = it },
            label = {
                Text(
                    text = if (isMandatory) label.plus("*") else label,
                    style = labelStyle
                )
            },
            placeholder = {
                Text(
                    text = "Type your ${label.lowercase()}",
                    style = placeholderStyle
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = imeAction,
                keyboardType = inputType
            ),
            modifier = modifier,
            keyboardActions = KeyboardActions(
                onNext = { focusRequester?.requestFocus() },
                onDone = { keyboardController?.hide() }
            ),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface),
            trailingIcon = {
                if (isInvalid && invalidState.value) {
                    Icon(
                        imageVector = errorIcon,
                        contentDescription = "error",
                        tint = iconTint
                    )
                } else {
                    icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = "icon trailing",
                            tint = iconTint
                        )
                    }
                }
            }
        )
        Spacer(
            modifier = modifier
                .matchParentSize()
                .background(Color.Transparent)
        )
    }
}