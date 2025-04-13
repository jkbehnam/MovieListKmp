package org.example.project

import io.ktor.client.engine.js.Js
import org.example.project.di.appModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(appModule(Js.create()))
    }
} 