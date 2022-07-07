package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.viverecollection.jetinferface.R

/**
 * Created by Annas Surdyanto on 07/06/22.
 *
 */

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    message: String = stringResource(id = R.string.no_data),
    buttonText: String = stringResource(id = R.string.retry),
    onClick: () -> Unit
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = modifier
                .wrapContentSize(Alignment.Center)
                .padding(sixteenDp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BodyText(
                    text = message,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = twelveDp)
                )
                PrimaryButton(text = buttonText, onClick = onClick)
            }
        }
    }
}