package com.viverecollection.jetinferface.ui.component

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
fun CommonTextField(
    modifier: Modifier = Modifier,
    label: String,
    state: MutableState<String>? = null,
    focusRequester: FocusRequester? = null,
    inputType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    enabled: Boolean = true,
    errorIcon: ImageVector = Icons.Filled.Error,
    invalidState: MutableState<Boolean> = mutableStateOf(false),
    labelStyle: TextStyle = MaterialTheme.typography.caption,
    placeholderStyle: TextStyle = MaterialTheme.typography.caption.copy(
        colorResource(id = R.color.colorHint)
    ),
    icon: ImageVector? = null,
    iconTint: Color = MaterialTheme.colors.secondaryVariant,
    isInvalid: Boolean = false,
    isMandatory: Boolean = false,
    onDone: (() -> Unit)? = null
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = state?.value ?: "",
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
            onDone = {
                onDone?.invoke()
                keyboardController?.hide()
            }
        ),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface),
        enabled = enabled,
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
}