package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.viverecollection.jetinferface.R

/**
 * Created by Annas Surdyanto on 29/09/22.
 *
 */

@Composable
fun TwinButton(
    modifier: Modifier = Modifier,
    onLeftClick: () -> Unit,
    leftLabel: String = stringResource(id = R.string.cancel),
    leftEnabled: Boolean = true,
    onRightClick: () -> Unit,
    rigthEnabled: Boolean = true,
    rigthLabel: String = stringResource(id = R.string.okay),
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (leftRef, rightRef) = createRefs()

        SecondaryButton(
            modifier = Modifier
                .defaultMinSize(minWidth = 120.dp)
                .constrainAs(leftRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(rightRef.start, margin = eightDp)
                },
            text = leftLabel,
            onClick = onLeftClick,
            enabled = leftEnabled
        )

        PrimaryButton(
            modifier = Modifier
                .defaultMinSize(minWidth = 120.dp)
                .constrainAs(rightRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(leftRef.end)
                },
            text = rigthLabel,
            onClick = onRightClick,
            enabled = rigthEnabled
        )
    }
}

/**
 * All buttons using primary color*/
@Composable
fun TwinEqualButton(
    modifier: Modifier = Modifier,
    onLeftClick: () -> Unit,
    leftLabel: String = stringResource(id = R.string.cancel),
    leftEnabled: Boolean = true,
    onRightClick: () -> Unit,
    rigthEnabled: Boolean = true,
    rigthLabel: String = stringResource(id = R.string.okay),
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (leftRef, rightRef) = createRefs()

        PrimaryButton(
            modifier = Modifier
                .defaultMinSize(minWidth = 120.dp)
                .constrainAs(leftRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(rightRef.start, margin = eightDp)
                },
            text = leftLabel,
            onClick = onLeftClick,
            enabled = leftEnabled
        )

        PrimaryButton(
            modifier = Modifier
                .defaultMinSize(minWidth = 120.dp)
                .constrainAs(rightRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(leftRef.end)
                },
            text = rigthLabel,
            onClick = onRightClick,
            enabled = rigthEnabled
        )
    }
}