package com.example.prahari.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val PrahariDarkColorScheme = darkColorScheme(

    primary = BluePrimary,

    secondary = BlueSecondary,

    background = Background,

    surface = Surface,

    error = Warning,

    onPrimary = White,

    onSecondary = White,

    onBackground = White,

    onSurface = White

)

@Composable
fun PrahariTheme(

    content: @Composable () -> Unit

) {

    MaterialTheme(

        colorScheme = PrahariDarkColorScheme,

        typography = Typography,

        content = content

    )

}