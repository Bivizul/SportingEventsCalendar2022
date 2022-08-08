package com.bivizul.sportingeventscalendar2022.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = d6,
    primaryVariant = d5,
    onPrimary = Color.Yellow,
    secondary = l2,
    surface = d1,
)

private val LightColorPalette = lightColors(
    primary = l6,
    primaryVariant = l5,
    onPrimary = Color.Yellow,
    secondary = l2,
    surface = l1,

    )

@Composable
fun SportingEventsCalendar2022Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val systemUiController = rememberSystemUiController()

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    if (darkTheme) {
        systemUiController.setSystemBarsColor(
            color = DarkColorPalette.surface
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = LightColorPalette.surface
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}