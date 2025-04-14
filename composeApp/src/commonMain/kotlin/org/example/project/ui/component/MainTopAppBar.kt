package org.example.project.ui.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import movielistkmp.composeapp.generated.resources.Res
import movielistkmp.composeapp.generated.resources.ic_arrow_back
import movielistkmp.composeapp.generated.resources.ic_dark_mode
import movielistkmp.composeapp.generated.resources.ic_light_mode
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainTopAppBar(
    darkTheme: Boolean? = false,
    changeTheme: () -> Unit,
    goBack: () -> Unit,
    showBackButton: Boolean = false,
    appName: String
) {
    TopAppBar(
        title = { Text(appName) }, actions = {
            darkTheme?.let {
                IconButton(onClick = { changeTheme() }) {
                    Icon(
                        painter = painterResource(if (darkTheme) Res.drawable.ic_dark_mode else Res.drawable.ic_light_mode),
                        contentDescription = "Localized description"
                    )
                }
            }
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { goBack() }) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back),
                        contentDescription = "Localized description"
                    )
                }
            }
        })
}