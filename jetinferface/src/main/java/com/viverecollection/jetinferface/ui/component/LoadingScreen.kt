package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created by Annas Surdyanto on 26/08/21.
 *
 */

@Composable
fun LoadingScreen() {
    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Column {
                CircularProgressIndicator()
            }
        }
    }
}


@Composable
@Preview
fun LoadingScreenPreview() {
    LoadingScreen()
}