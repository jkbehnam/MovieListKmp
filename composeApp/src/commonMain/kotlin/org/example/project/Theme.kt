package org.example.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object AppTheme {
    object Light {
        val primary = Color(0xFF6200EE)
        val surface = Color.White
        val onSurface = Color.Black
        val background = Color.White
    }

    object Dark {
        val primary = Color(0xFFBB86FC)
        val surface = Color(0xFF121212)
        val onSurface = Color.White
        val background = Color(0xFF121212)
    }
}

@Composable
fun AppTheme(
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isDarkTheme) {
            MaterialTheme.colors.copy(
                primary = AppTheme.Dark.primary,
                surface = AppTheme.Dark.surface,
                onSurface = AppTheme.Dark.onSurface,
                background = AppTheme.Dark.background
            )
        } else {
            MaterialTheme.colors.copy(
                primary = AppTheme.Light.primary,
                surface = AppTheme.Light.surface,
                onSurface = AppTheme.Light.onSurface,
                background = AppTheme.Light.background
            )
        },
        content = content
    )
} 