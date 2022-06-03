package com.viverecollection.jetinferface.ui.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viverecollection.jetinferface.R

/**
 * Created by Annas Surdyanto on 31/08/21.
 *
 */

@Composable
fun AppUpdateDialogScreen(
    isForceUpdate: Boolean,
    appLink: String,
    onDismiss: () -> Unit
) {
    val isShowDialog = remember { mutableStateOf(true) }
    val message =
        if (isForceUpdate) stringResource(R.string.force_update_message) else stringResource(R.string.flexible_update_message)
    val context = LocalContext.current as AppCompatActivity
    if (isShowDialog.value) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(text = stringResource(id = R.string.app_update_title)) },
            text = { Text(text = message) },
            confirmButton = {
                PrimaryButton(
                    text = stringResource(id = R.string.update),
                    onClick = {
                        getPlayStore(context, appLink)
                        if (!isForceUpdate) {
                            isShowDialog.value = false
                            onDismiss()
                        }
                    }
                )
            },
            dismissButton = {
                SecondaryButton(
                    text = stringResource(id = R.string.later),
                    onClick = {
                        isShowDialog.value = false
                        onDismiss()
                    }
                )
            }
        )
    }
}

private fun getPlayStore(context: Context, appUrl: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(appUrl)
    context.startActivity(intent)
}

@Composable
@Preview
fun AppUpdateDialogScreenPreview() {
    AppUpdateDialogScreen(
        isForceUpdate = true,
        appLink = "An error occurred. Retry?",
        onDismiss = {}
    )
}