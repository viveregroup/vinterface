package com.viverecollection.jetinferface.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import id.co.vivere.util.*
import com.viverecollection.jetinferface.R

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
    label: String,
    style: TextStyle = MaterialTheme.typography.body1,
    icon: ImageVector,
    backgroundColor: Color = MaterialTheme.colors.background,
    foregroundColor: Color = MaterialTheme.colors.onBackground,
    borderColor: Color = MaterialTheme.colors.secondary,
    shape: Shape = RoundedCornerShape(10.dp),
    onButtonClicked: (() -> Unit)? = null,
    onIconClicked: (() -> Unit)? = null,
) {
    val buttonModifier =
        if (onButtonClicked != null) modifier.clickable(onClick = onButtonClicked)
        else modifier
    Column(
        modifier = buttonModifier
            .background(backgroundColor, shape = shape)
            .border(1.dp, borderColor, shape)
            .padding(keyLine2)
    ) {
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = label,
                style = style.copy(color = foregroundColor)
            )
            val iconModifier =
                if (onIconClicked != null) Modifier.clickable(onClick = onIconClicked)
                else Modifier
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = foregroundColor,
                modifier = iconModifier
                    .padding(start = keyLine2)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun LeadingIconButton(
    modifier: Modifier = Modifier,
    label: String,
    style: TextStyle = MaterialTheme.typography.body1,
    icon: ImageVector = Icons.Filled.Email,
    backgroundColor: Color = MaterialTheme.colors.background,
    foregroundColor: Color = MaterialTheme.colors.onBackground,
    borderColor: Color = MaterialTheme.colors.secondary,
    shape: Shape = RoundedCornerShape(10.dp),
    onIconClicked: (() -> Unit)? = null,
    onButtonClicked: (() -> Unit)? = null
) {
    val buttonModifier =
        if (onButtonClicked != null) modifier.clickable(onClick = onButtonClicked)
        else modifier

    val iconModifier =
        if (onIconClicked != null) Modifier.clickable(onClick = onIconClicked)
        else Modifier
    Column(
        modifier = buttonModifier
            .background(backgroundColor, shape = shape)
            .border(1.dp, borderColor, shape)
            .padding(keyLine2)
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = foregroundColor,
                modifier = iconModifier.align(Alignment.CenterVertically)
            )
            Spacer(size = 4.dp)
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = label,
                style = style.copy(color = foregroundColor)
            )
        }
    }
}

@Composable
fun CopyButton(
    modifier: Modifier = Modifier,
    label: String,
    content: String,
    style: TextStyle = MaterialTheme.typography.body1,
    message: String = stringResource(id = R.string.text_copied),
    icon: ImageVector = Icons.Filled.ContentCopy,
    backgroundColor: Color = MaterialTheme.colors.background,
    foregroundColor: Color = MaterialTheme.colors.onBackground,
    borderColor: Color = MaterialTheme.colors.secondary,
    shape: Shape = RoundedCornerShape(10.dp)
) {
    val context = LocalContext.current
    TrailingIconButton(
        modifier = modifier,
        style = style,
        label = label,
        icon = icon,
        backgroundColor = backgroundColor,
        foregroundColor = foregroundColor,
        borderColor = borderColor,
        shape = shape
    ) {
        content.copyToClipboard(context = context, message)
    }
}


@Composable
fun ShareButton(
    modifier: Modifier = Modifier,
    label: String,
    content: String,
    style: TextStyle = MaterialTheme.typography.body1,
    icon: ImageVector = Icons.Filled.Share,
    backgroundColor: Color = MaterialTheme.colors.background,
    foregroundColor: Color = MaterialTheme.colors.onBackground,
    borderColor: Color = MaterialTheme.colors.secondary,
    shape: Shape = RoundedCornerShape(10.dp)
) {
    val context = LocalContext.current
    TrailingIconButton(
        modifier = modifier,
        style = style,
        label = label,
        icon = icon,
        backgroundColor = backgroundColor,
        foregroundColor = foregroundColor,
        borderColor = borderColor,
        shape = shape
    ) {
        content.shareText(context = context)
    }
}

@Composable
fun EmailAddressButton(
    modifier: Modifier = Modifier,
    address: String,
    style: TextStyle = MaterialTheme.typography.body1,
    icon: ImageVector = Icons.Filled.Email,
    backgroundColor: Color = MaterialTheme.colors.background,
    foregroundColor: Color = MaterialTheme.colors.onBackground,
    borderColor: Color = MaterialTheme.colors.secondary,
    shape: Shape = RoundedCornerShape(10.dp)
) {
    val context = LocalContext.current
    LeadingIconButton(
        modifier = modifier,
        style = style,
        label = address,
        icon = icon,
        backgroundColor = backgroundColor,
        foregroundColor = foregroundColor,
        borderColor = borderColor,
        shape = shape
    ) {
        address.sendEmail(context)
    }
}


@Composable
fun PhoneNumberButton(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    style: TextStyle = MaterialTheme.typography.body1,
    icon: ImageVector = Icons.Filled.Phone,
    backgroundColor: Color = MaterialTheme.colors.background,
    foregroundColor: Color = MaterialTheme.colors.onBackground,
    borderColor: Color = MaterialTheme.colors.secondary,
    shape: Shape = RoundedCornerShape(10.dp)
) {
    val context = LocalContext.current
    LeadingIconButton(
        modifier = modifier,
        style = style,
        label = phoneNumber,
        icon = icon,
        backgroundColor = backgroundColor,
        foregroundColor = foregroundColor,
        borderColor = borderColor,
        shape = shape
    ) {
        phoneNumber.callPhone(context)
    }
}

@Composable
fun DirectUrlButton(
    modifier: Modifier = Modifier,
    label: String,
    url: String,
    style: TextStyle = MaterialTheme.typography.body1,
    icon: ImageVector = Icons.Filled.Web,
    backgroundColor: Color = MaterialTheme.colors.background,
    foregroundColor: Color = MaterialTheme.colors.onBackground,
    borderColor: Color = MaterialTheme.colors.secondary,
    shape: Shape = RoundedCornerShape(10.dp)
) {
    val context = LocalContext.current
    LeadingIconButton(
        modifier = modifier,
        style = style,
        label = label,
        icon = icon,
        backgroundColor = backgroundColor,
        foregroundColor = foregroundColor,
        borderColor = borderColor,
        shape = shape
    ) {
        url.openUrl(context)
    }
}