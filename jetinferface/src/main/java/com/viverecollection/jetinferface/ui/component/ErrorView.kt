package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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

@Composable
fun ErrorDialogView(
    throwableMessage: String? = null,
    serverErrorMessage: String?,
    errorTitle: String = stringResource(id = R.string.error_title),
    onRetry: () -> Unit,
    onCancel: (() -> Unit)? = null
) {
    if (throwableMessage != null) {
        RetryDialog(
            title = errorTitle,
            message = throwableMessage,
            onConfirmed = onRetry,
            onCancel = onCancel
        )
    } else if (!serverErrorMessage.isNullOrEmpty())
        RetryDialog(
            title = errorTitle,
            message = serverErrorMessage,
            onConfirmed = onRetry,
            onCancel = onCancel
        )
}

@Composable
fun AppSurface(
    modifier: Modifier = Modifier,
    loadingText: String = stringResource(id = R.string.loading_data),
    throwableMessage: String? = null,
    serverErrorMessage: String?,
    showEmptyData: Boolean,
    isLoading: Boolean,
    onRetry: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(modifier = modifier.fillMaxSize()) {
        if (throwableMessage != null) {
            SurfaceEmptyContentView(
                message = throwableMessage,
                onClick = onRetry,
                isLoading = isLoading,
                loadingText = loadingText
            )
        } else if (!serverErrorMessage.isNullOrEmpty())
            SurfaceEmptyContentView(
                message = serverErrorMessage,
                onClick = onRetry,
                isLoading = isLoading,
                loadingText = loadingText
            )
        else if (showEmptyData && serverErrorMessage.isNullOrEmpty()) SurfaceEmptyContentView(
            onClick = onRetry,
            isLoading = isLoading,
            loadingText = loadingText
        ) else content(it)
    }
}

@Composable
fun EmptyContentHandler(
    loadingText: String = stringResource(id = R.string.loading_data),
    throwableMessage: String? = null,
    serverErrorMessage: String?,
    showEmptyData: Boolean,
    isLoading: Boolean,
    onRetry: () -> Unit
) {
    if (throwableMessage != null) {
        SurfaceEmptyContentView(
            message = throwableMessage,
            onClick = onRetry,
            isLoading = isLoading,
            loadingText = loadingText
        )
    } else if (!serverErrorMessage.isNullOrEmpty())
        SurfaceEmptyContentView(
            message = serverErrorMessage,
            onClick = onRetry,
            isLoading = isLoading,
            loadingText = loadingText
        )
    else if (showEmptyData && serverErrorMessage.isNullOrEmpty()) SurfaceEmptyContentView(
        onClick = onRetry,
        isLoading = isLoading,
        loadingText = loadingText
    )
}


@Composable
fun SurfaceEmptyContentView(
    modifier: Modifier = Modifier,
    loadingText: String = stringResource(id = R.string.loading_data),
    message: String = stringResource(id = R.string.no_data),
    isLoading: Boolean,
    buttonText: String = stringResource(id = R.string.retry),
    onClick: () -> Unit
) {
    val text = if (isLoading) loadingText else message
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(sixteenDp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BodyText(
                text = text,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = twelveDp)
            )
            if (!isLoading) PrimaryButton(
                text = buttonText,
                onClick = onClick,
                modifier = Modifier.height(40.dp)
            )
        }
    }
}


@Composable
fun EmptyView(
    modifier: Modifier = Modifier,
    message: String,
    onClick: (() -> Unit)? = null,
    buttonLabel: String = stringResource(id = R.string.retry)
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(sixteenDp),
        contentAlignment = Alignment.Center
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
            if (onClick != null) PrimaryButton(
                text = buttonLabel,
                onClick = onClick,
                modifier = Modifier.height(40.dp)
            )
        }
    }
}