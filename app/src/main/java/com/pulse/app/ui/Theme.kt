package com.pulse.app.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkBackground = Color(0xFF1E1F22)
val DarkPanel = Color(0xFF2B2D31)
val DarkSidebar = Color(0xFF1A1B1E)
val DarkHover = Color(0xFF35373C)
val DarkDivider = Color(0xFF3F4147)
val TextPrimary = Color(0xFFF2F3F5)
val TextSecondary = Color(0xFFB5BAC1)
val PulseAccent = Color(0xFF5865F2)
val PulseOnline = Color(0xFF23A55A)
val PulseDanger = Color(0xFFED4245)

private val PulseDarkColors = darkColorScheme(
    primary = PulseAccent,
    onPrimary = Color.White,
    background = DarkBackground,
    onBackground = TextPrimary,
    surface = DarkPanel,
    onSurface = TextPrimary,
    surfaceVariant = DarkHover,
    onSurfaceVariant = TextSecondary,
    error = PulseDanger,
    outline = DarkDivider,
)

@Composable
fun PulseTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = PulseDarkColors, content = content)
}
