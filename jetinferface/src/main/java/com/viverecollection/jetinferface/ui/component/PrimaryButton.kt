package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.viverecollection.jetinferface.R
import com.viverecollection.jetinferface.util.*

/**
 * Created by Annas Surdyanto on 07/09/21.
 *
 */

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
    textStyle: TextStyle = MaterialTheme.typography.caption.copy(
        color = Color.White,
        fontWeight = FontWeight.SemiBold
    ),
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        colors = buttonColors
    ) {
        Text(
            text = text,
            style = textStyle,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun UnselectedButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.unselected)),
    icon: ImageVector? = null,
    iconTint: Color = MaterialTheme.colors.surface,
    textStyle: TextStyle = MaterialTheme.typography.caption.copy(
        color = Color.White,
        fontWeight = FontWeight.SemiBold
    ),
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        colors = buttonColors
    ) {
        Row {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = "button icon",
                    tint = iconTint
                )
            }
            Spacer()
            Text(
                text = text,
                style = textStyle,
                modifier = Modifier.align(Alignment.CenterVertically),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.SemiBold),
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        colors = buttonColors
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
    textStyle: TextStyle = MaterialTheme.typography.body1,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        colors = buttonColors
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}

@Composable
fun BorderedSecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonColors: ButtonColors = ButtonDefaults.outlinedButtonColors(backgroundColor = MaterialTheme.colors.background),
    textStyle: TextStyle = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.SemiBold),
    border: BorderStroke = BorderStroke(1.dp, color = MaterialTheme.colors.primary),
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        colors = buttonColors,
        border = border
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}

@Composable
fun TrailingIconButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    label: String,
    style: TextStyle = MaterialTheme.typography.body1,
    icon: ImageVector,
    iconTint: Color = MaterialTheme.colors.primary,
    elevation: Dp = fourDp,
    shape: Shape = RoundedCornerShape(10.dp),
    onClicked: (() -> Unit)? = null,
) {
    val buttonModifier =
        if (onClicked != null) modifier.clickable(onClick = onClicked)
        else modifier
    Card(
        modifier = buttonModifier.padding(bottom = eightDp),
        shape = shape,
        elevation = elevation
    ) {
        Box(
            modifier = Modifier.padding(eightDp)
        ) {
            Row(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = label,
                    style = style
                )
                Spacer(size = 4.dp)
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = iconTint,
                    modifier = iconModifier,
                )
            }
        }
    }
}

@Composable
fun LeadingIconButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    label: String,
    style: TextStyle = MaterialTheme.typography.body1,
    icon: ImageVector = Icons.Filled.Email,
    iconTint: Color = MaterialTheme.colors.primary,
    shape: Shape = RoundedCornerShape(fourDp),
    elevation: Dp = fourDp,
    onClicked: (() -> Unit)? = null
) {
    val buttonModifier =
        if (onClicked != null) modifier.clickable(onClick = onClicked)
        else modifier
    Card(
        modifier = buttonModifier.padding(bottom = eightDp),
        shape = shape,
        elevation = elevation
    ) {
        Box(
            modifier = Modifier.padding(eightDp)
        ) {
            Row(modifier = Modifier.align(Alignment.Center)) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = iconTint,
                    modifier = iconModifier,
                )
                Spacer(size = 4.dp)
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = label,
                    style = style
                )
            }
        }
    }
}

@Composable
fun CopyButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    label: String,
    content: String,
    style: TextStyle = MaterialTheme.typography.body1,
    message: String = stringResource(id = R.string.text_copied),
    icon: ImageVector = Icons.Filled.ContentCopy,
    iconTint: Color = MaterialTheme.colors.primary,
    shape: Shape = RoundedCornerShape(10.dp)
) {
    val context = LocalContext.current
    TrailingIconButton(
        modifier = modifier,
        style = style,
        label = label,
        icon = icon,
        shape = shape,
        iconModifier = iconModifier,
        iconTint = iconTint
    ) {
        content.copyToClipboard(context = context, message)
    }
}


@Composable
fun ShareButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    label: String,
    content: String,
    style: TextStyle = MaterialTheme.typography.body1,
    icon: ImageVector = Icons.Filled.Share,
    iconTint: Color = MaterialTheme.colors.primary,
    shape: Shape = RoundedCornerShape(10.dp)
) {
    val context = LocalContext.current
    TrailingIconButton(
        modifier = modifier,
        style = style,
        label = label,
        icon = icon,
        shape = shape,
        iconModifier = iconModifier,
        iconTint = iconTint
    ) {
        content.shareText(context = context)
    }
}

@Composable
fun EmailAddressButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    address: String,
    style: TextStyle = MaterialTheme.typography.body1,
    icon: ImageVector = Icons.Filled.Email,
    iconTint: Color = MaterialTheme.colors.primary,
    shape: Shape = RoundedCornerShape(10.dp)
) {
    val context = LocalContext.current
    LeadingIconButton(
        modifier = modifier,
        style = style,
        label = address,
        icon = icon,
        shape = shape,
        iconModifier = iconModifier,
        iconTint = iconTint
    ) {
        address.sendEmail(context)
    }
}


@Composable
fun PhoneNumberButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    phoneNumber: String,
    style: TextStyle = MaterialTheme.typography.body1,
    icon: ImageVector = Icons.Filled.Phone,
    iconTint: Color = MaterialTheme.colors.primary,
    shape: Shape = RoundedCornerShape(10.dp)
) {
    val context = LocalContext.current
    LeadingIconButton(
        modifier = modifier,
        style = style,
        label = phoneNumber,
        icon = icon,
        shape = shape,
        iconModifier = iconModifier,
        iconTint = iconTint
    ) {
        phoneNumber.callPhone(context)
    }
}

@Composable
fun DirectUrlButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    label: String,
    url: String,
    style: TextStyle = MaterialTheme.typography.body1,
    icon: ImageVector = Icons.Filled.Web,
    iconTint: Color = MaterialTheme.colors.primary,
    shape: Shape = RoundedCornerShape(10.dp)
) {
    val context = LocalContext.current
    LeadingIconButton(
        modifier = modifier,
        style = style,
        label = label,
        icon = icon,
        shape = shape,
        iconModifier = iconModifier,
        iconTint = iconTint
    ) {
        url.openUrl(context)
    }
}