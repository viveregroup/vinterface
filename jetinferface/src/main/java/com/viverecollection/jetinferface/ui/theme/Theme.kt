package com.viverecollection.jetinferface.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable

@Composable
fun LightTheme(
    content: @Composable () -> Unit,
    shapes: Shapes = Shapes(),
    typography: Typography = Typography,
    colors: Colors = lightColors()
) {
    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@Composable
fun DarkTheme(
    content: @Composable () -> Unit,
    shapes: Shapes = Shapes(),
    typography: Typography = Typography,
    colors: Colors = darkColors()
) {
    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@Composable
fun AppTheme(darkTheme: @Composable (() -> Unit)? = null, lightTheme: @Composable () -> Unit) {
    val isDarkTheme = isSystemInDarkTheme()
    return if (isDarkTheme && darkTheme != null) darkTheme()
    else lightTheme()
}