package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * https://johncodeos.com/how-to-add-search-in-list-with-jetpack-compose/
 *
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    state: MutableState<String>,
    label: String,
    style: TextStyle = MaterialTheme.typography.body1,
    onTextChanged: ((String) -> Unit)? = null,
    onReset:(() -> Unit)? = null,
    onSearch: ((String) -> Unit)? = null
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
            onTextChanged?.invoke(value)
        },
        modifier = modifier,
        label = { BodyText(label, style = style) },
        textStyle = style,
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "search icon",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != "") {
                IconButton(onClick = {
                    state.value = ""
                    onTextChanged?.invoke("")
                    onReset?.invoke()
                    keyboardController?.hide()
                }) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                onSearch?.invoke(state.value)
            }
        ),
        singleLine = true,
        shape = RoundedCornerShape(10.dp), // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf("") }
    SearchView(state = textState, label = "test") {}
}