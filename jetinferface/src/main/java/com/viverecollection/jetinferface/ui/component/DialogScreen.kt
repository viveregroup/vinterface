package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viverecollection.jetinferface.R

/**
 * Created by Annas Surdyanto on 31/08/21.
 *
 */

@Composable
fun DialogScreen(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    positiveText: String = stringResource(R.string.yes),
    negativeText: String = stringResource(R.string.no),
    onConfirmed: (() -> Unit?)? = null,
    onCancel: (() -> Unit?)? = null,
    isShowDialog: MutableState<Boolean>
) {
    if (isShowDialog.value) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = { },
            title = { Text(text = title) },
            text = { Text(text = message) },
            confirmButton = {
                PrimaryButton(
                    modifier = Modifier.padding(bottom = eightDp),
                    text = positiveText,
                    onClick = {
                        isShowDialog.value = false
                        onConfirmed?.let { it() }
                    }
                )
            },
            dismissButton =
            {
                SecondaryButton(
                    modifier = Modifier.padding(bottom = eightDp),
                    text = negativeText,
                    onClick = {
                        isShowDialog.value = false
                        onCancel?.let { it() }
                    }
                )
            }
        )
    }
}

@Composable
fun OptionDialog(
    modifier: Modifier = Modifier,
    title: String = "Confirmation",
    message: String,
    onConfirmed: (() -> Unit?)? = null,
    onCancel: (() -> Unit?)? = null
) {
    val isShowDialog = remember { mutableStateOf(true) }
    DialogScreen(
        modifier = modifier,
        title = title,
        message = message,
        onConfirmed = onConfirmed,
        isShowDialog = isShowDialog,
        onCancel = onCancel
    )
}

@Composable
fun RetryDialog(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.error_title),
    message: String,
    positiveText: String = stringResource(id = R.string.retry),
    negativeText: String = stringResource(id = R.string.cancel),
    onConfirmed: (() -> Unit?)? = null,
    onCancel: (() -> Unit?)? = null
) {
    val isShowDialog = remember { mutableStateOf(true) }
    DialogScreen(
        modifier = modifier,
        title = title,
        message = message,
        positiveText = positiveText,
        negativeText = negativeText,
        onConfirmed = onConfirmed,
        isShowDialog = isShowDialog,
        onCancel = onCancel
    )
}

@Composable
fun MultipleRetryDialog(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.error_title),
    message: String,
    positiveText: String = stringResource(id = R.string.retry),
    negativeText: String = stringResource(id = R.string.cancel),
    onConfirmed: (() -> Unit?)? = null,
    onCancel: (() -> Unit?)? = null,
    isShowDialog: MutableState<Boolean>
) {
    DialogScreen(
        modifier = modifier,
        title = title,
        message = message,
        positiveText = positiveText,
        negativeText = negativeText,
        onConfirmed = onConfirmed,
        isShowDialog = isShowDialog,
        onCancel = onCancel
    )
}

@Composable
fun OkayErrorDialog(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.error_title),
    message: String,
    positiveText: String = stringResource(id = R.string.okay),
    onConfirmed: (() -> Unit?)? = null
) {
    val isShowDialog = remember { mutableStateOf(true) }
    if (isShowDialog.value) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = { },
            title = { Text(text = title) },
            text = { Text(text = message) },
            confirmButton = {
                PrimaryButton(
                    modifier = Modifier.padding(bottom = eightDp),
                    text = positiveText,
                    onClick = {
                        onConfirmed?.let { it() }
                        isShowDialog.value = false
                    }
                )
            }
        )
    }
}

@Composable
fun OkaySuccessDialog(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.success),
    message: String,
    positiveText: String = stringResource(id = R.string.okay),
    onConfirmed: (() -> Unit?)? = null
) {
    val isShowDialog = remember { mutableStateOf(true) }
    if (isShowDialog.value) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = { },
            title = { Text(text = title) },
            text = { Text(text = message) },
            confirmButton = {
                PrimaryButton(
                    modifier = Modifier.padding(bottom = eightDp),
                    text = positiveText,
                    onClick = {
                        onConfirmed?.let { it() }
                        isShowDialog.value = false
                    }
                )
            }
        )
    }
}

@Composable
@Preview
fun DialogScreenPreview() {
    DialogScreen(
        title = "Error",
        message = "An error occurred. Retry?",
        positiveText = "Yes",
        negativeText = "No",
        onConfirmed = { },
        onCancel = {},
        isShowDialog = remember { mutableStateOf(false) }
    )
}