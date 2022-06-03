package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.viverecollection.jetinferface.R

/**
 * Created by Annas Surdyanto on 13/09/21.
 *
 */


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OutlinedPasswordTextField(
    modifier: Modifier = Modifier,
    label: String,
    state: MutableState<String>? = null,
    focusRequester: FocusRequester? = null,
    inputType: KeyboardType = KeyboardType.Password,
    imeAction: ImeAction = ImeAction.Default,
    enabled: Boolean = true,
    onDone: (() -> Unit)? = null
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val passwordVisibility = remember { mutableStateOf(false) }
    val labelString = label.replace("*", "")
    OutlinedTextField(
        value = state?.value ?: "",
        onValueChange = { state?.value = it },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.caption
            )
        },
        placeholder = {
            Text(
                text = "Type your $labelString",
                style = MaterialTheme.typography.caption.copy(colorResource(id = R.color.colorHint))
            )
        },
        visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
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
            val image = if (passwordVisibility.value)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                Icon(imageVector = image, "password visibility")
            }
        }
    )
}