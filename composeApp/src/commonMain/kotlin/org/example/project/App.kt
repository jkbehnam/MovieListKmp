package org.example.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.example.project.navigation.AppNavigation
import org.example.project.ui.component.MainTopAppBar
import org.example.project.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(

) {

    var darkTheme by remember { mutableStateOf(false) }
    val navController = rememberNavController()

    val backStack by navController.currentBackStack.collectAsState()

    Theme (darkTheme = darkTheme) {
        Scaffold(topBar = {
            MainTopAppBar(
                appName = "TMDB List",
                darkTheme = darkTheme,
                changeTheme = { darkTheme = !darkTheme },
                showBackButton= backStack.size > 2,
                goBack = {
                    navController.popBackStack()
                }
            )
        }) { padding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                AppNavigation(navController)
            }
        }
    }
}
