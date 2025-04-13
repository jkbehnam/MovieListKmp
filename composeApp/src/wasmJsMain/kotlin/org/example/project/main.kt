package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.jetbrains.compose.web.renderComposable

fun main() {
    initKoin()
    renderComposable(rootElementId = "root") {
        App()
    }
}