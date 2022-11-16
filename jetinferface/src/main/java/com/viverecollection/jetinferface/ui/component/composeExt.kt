package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable

/**
 * Created by Annas Surdyanto on 18/10/22.
 *
 */
@OptIn(ExperimentalFoundationApi::class)
fun LazyGridScope.header(
    content: @Composable LazyItemScope.() -> Unit
) {
    item(span = { GridItemSpan(this.maxCurrentLineSpan) }, content = content)
}