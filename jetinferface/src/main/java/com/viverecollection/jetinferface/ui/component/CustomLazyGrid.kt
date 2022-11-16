package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

/**
 * Created by Annas Surdyanto on 15/06/22.
 *
 */

fun <T> LazyListScope.CustomLazyGrid(data: List<T>, content: @Composable (Modifier, T) -> Unit) {
    for (i in data.indices) {
        if (i % 2 == 0) {
            val list = mutableListOf<T>()
            list.add(data[i])
            if (i != data.size-1) list.add(data[i + 1])
            item {
                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                    val (leftRef, rightRef) = createRefs()
                    val leftModifier = Modifier
                        .constrainAs(leftRef) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(rightRef.start, margin = fourDp)
                            width = Dimension.fillToConstraints
                        }

                    val rightModifier = Modifier
                        .constrainAs(rightRef) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            start.linkTo(leftRef.end, margin = fourDp)
                            width = Dimension.fillToConstraints
                        }

                    content(leftModifier, data[i])
                    if (i != data.size-1) content(rightModifier, data[i + 1])
                }
            }
        }
    }
}