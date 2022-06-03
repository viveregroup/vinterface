package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * Created by Annas Surdyanto on 16/08/21.
 *
 */
@Composable
fun Spacer(
    modifier: Modifier = Modifier,
    size: Dp = keyLine2
) {
    Spacer(modifier = modifier.padding(size))
}