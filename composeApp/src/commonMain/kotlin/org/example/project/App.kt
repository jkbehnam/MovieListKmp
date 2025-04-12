package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import movielistkmp.composeapp.generated.resources.Res
import movielistkmp.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    var isDarkTheme by remember { mutableStateOf(false) }
    
    AppTheme(isDarkTheme = isDarkTheme) {
        Surface {
            var showContent by remember { mutableStateOf(false) }
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { isDarkTheme = !isDarkTheme },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            imageVector = if (isDarkTheme) Icons.Default.Face else Icons.Default.Done,
                            contentDescription = "Toggle Theme"
                        )
                    }
                }
                
                Button(onClick = { showContent = !showContent }) {
                    Text("Click me!")
                }
                Text(
                    text = "Welcome to Movie List App",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(top = 16.dp)
                )
                AnimatedVisibility(showContent) {
                    val greeting = remember { Greeting().greet() }
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                        Text("Compose: $greeting")
                    }
                }
            }
        }
    }
}