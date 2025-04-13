package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.ktor.client.engine.cio.CIO
import org.example.project.di.appModule
import org.koin.core.context.GlobalContext.startKoin

fun main() {
    // Create an HTTP engine instance
    val httpEngine = CIO.create()  // You can replace CIO with another engine like OkHttp, Apache, etc.

    // Initialize Koin before starting the application
    startKoin {
        modules(appModule(httpEngine))
    }

    // Then start your application
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "MovieListKmp",
        ) {
            App()
        }
    }
}