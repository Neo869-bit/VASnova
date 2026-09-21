package com.vasnova.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val VasNovaDarkColorScheme = darkColorScheme(
    primary = CyberEmerald,
    onPrimary = Obsidian,
    primaryContainer = NeonGreen,
    onPrimaryContainer = PureWhite,
    secondary = NeonGreen,
    onSecondary = Obsidian,
    background = Obsidian,
    onBackground = PureWhite,
    surface = CharcoalCard,
    onSurface = PureWhite,
    surfaceVariant = CharcoalCard,
    onSurfaceVariant = SilverMuted,
    outline = SlateBorder,
    error = ErrorNeon,
    onError = PureWhite,
)

@Composable
fun VasNovaTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        else -> VasNovaDarkColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = VasNovaTypography,
        content = content,
    )
}
