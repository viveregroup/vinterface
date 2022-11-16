package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Created by Annas Surdyanto on 07/11/22.
 *
 */


@Composable
fun CustomCheckBox(
    modifier: Modifier = Modifier,
    label: String,
    isChecked: Boolean,
    enabled: Boolean = true,
    onCheckChanged: (Boolean) -> Unit
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = isChecked, onCheckedChange = onCheckChanged, enabled = enabled)
        Text(text = label)
    }
}