package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.viverecollection.jetinferface.R
import id.co.vivere.util.toAmount
import id.co.vivere.util.toIdr

/**
 * Created by Annas Surdyanto on 13/09/21.
 *
 */


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CurrencyTextField(
    modifier: Modifier = Modifier,
    label: String,
    state: MutableState<String>,
    inputType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Done,
    enabled: Boolean = true,
    errorIcon: ImageVector = Icons.Filled.Error,
    icon: ImageVector? = null,
    invalidState: MutableState<Boolean> = mutableStateOf(false),
    labelStyle: TextStyle = MaterialTheme.typography.caption,
    placeholderStyle: TextStyle = MaterialTheme.typography.caption.copy(
        colorResource(id = R.color.colorHint)
    ),
    isMandatory: Boolean = false,
    isInvalid: Boolean = false
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val textFieldValueState = remember {
        mutableStateOf(
            TextFieldValue(
                text = state.value.toIdr(),
                selection = TextRange(state.value.toIdr().length)
            )
        )
    }
    TextField(
        value = textFieldValueState.value,
        onValueChange = {
            textFieldValueState.value = TextFieldValue(
                text = it.text.toIdr(),
                selection = TextRange(it.text.toIdr().length)
            )
            state.value = it.text.toAmount()
        },
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
            onDone = {
                keyboardController?.hide()
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        enabled = enabled,
        trailingIcon = {
            if (isInvalid && invalidState.value) {
                Icon(
                    imageVector = errorIcon,
                    contentDescription = "error"
                )
            } else {
                icon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "icon trailing",
                        tint = Color.Gray
                    )
                }
            }
        },
    )
}