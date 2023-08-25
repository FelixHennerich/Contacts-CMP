package me.hennerich.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import me.hennerich.ui.theme.DarkColorScheme
import me.hennerich.ui.theme.LightColorScheme
import me.hennerich.ui.theme.Typography

@Composable
actual fun ContactsTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
){
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}