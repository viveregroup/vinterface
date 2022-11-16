package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.viverecollection.jetinferface.R

/**
 * Created by Annas Surdyanto on 31/01/22.
 *
 */

@Composable
fun RadioButtonView(
    modifier: Modifier = Modifier,
    label: String,
    leftSelectionLabel: String = stringResource(id = R.string.yes),
    rightSelectionLabel: String = stringResource(id = R.string.no),
    isFirstSelected: MutableState<Boolean>,
    isInit: MutableState<Boolean>,
    textStyle: TextStyle = MaterialTheme.typography.caption
) {
    val onSelected: (Boolean) -> Unit = {
        isFirstSelected.value = it
        isInit.value = false
    }
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = label,
            style = textStyle
        )
        Spacer()
        RadioButton(
            selected = if (isInit.value) false else isFirstSelected.value,
            onClick = { onSelected(true) }
        )
        Text(
            text = leftSelectionLabel,
            modifier = Modifier
                .clickable(onClick = { onSelected(true) })
                .padding(start = 4.dp)
                .align(Alignment.CenterVertically),
            style = textStyle
        )
        Spacer()
        RadioButton(
            selected = if (isInit.value) false else !isFirstSelected.value,
            onClick = { onSelected(false) }
        )
        Text(
            text = rightSelectionLabel,
            modifier = Modifier
                .clickable(onClick = { onSelected(false) })
                .padding(start = 4.dp)
                .align(Alignment.CenterVertically),
            style = textStyle
        )
    }
}


/**
 * Created by Annas Surdyanto on 13/10/22.
 *
 * @param leftSelectionLabel is the left option text
 * @param rightSelectionLabel is the right option text
 * @param selection is the value from 0 to 2.
 *
 * - 0 = not selected
 *
 * - 1 = left selected
 *
 * - 2 = right selected
 *
 */

@Composable
fun RadioButtonOptionView(
    modifier: Modifier = Modifier,
    label: String,
    leftSelectionLabel: String = stringResource(id = R.string.yes),
    rightSelectionLabel: String = stringResource(id = R.string.no),
    selection: MutableState<Int>,
    textStyle: TextStyle = MaterialTheme.typography.caption,
    onSelectionChanged: ((Int) -> Unit)? = null
) {
    val onSelected: (Int) -> Unit = {
        selection.value = it
        onSelectionChanged?.invoke(it)
    }
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = label,
            style = textStyle
        )
        Spacer()
        RadioButton(
            selected = selection.value == 1,
            onClick = { onSelected(1) }
        )
        Text(
            text = leftSelectionLabel,
            modifier = Modifier
                .clickable(onClick = { onSelected(1) })
                .padding(start = 4.dp)
                .align(Alignment.CenterVertically),
            style = textStyle
        )
        Spacer()
        RadioButton(
            selected = selection.value == 2,
            onClick = { onSelected(2) }
        )
        Text(
            text = rightSelectionLabel,
            modifier = Modifier
                .clickable(onClick = { onSelected(2) })
                .padding(start = 4.dp)
                .align(Alignment.CenterVertically),
            style = textStyle
        )
    }
}