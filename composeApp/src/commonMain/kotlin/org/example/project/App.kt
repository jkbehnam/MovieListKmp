package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.example.project.presentation.MoviesViewModel
import movielistkmp.composeapp.generated.resources.Res
import movielistkmp.composeapp.generated.resources.compose_multiplatform
import org.example.project.domain.model.Movie
import org.example.project.domain.model.Response
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    var isDarkTheme by remember { mutableStateOf(false) }
    val viewModel: MoviesViewModel = koinViewModel()
    
    AppTheme(isDarkTheme = isDarkTheme) {
        Surface {
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
                Button(onClick = { viewModel.getMovies() }) {
                    Text("Get Movies")
                }
                
                Text(
                    text = "Welcome to Movie List App",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(top = 16.dp)
                )
                
                when (val state = viewModel.moviesState) {
                    is Response.Loading -> {
                        CircularProgressIndicator()
                    }
                    is Response.Success -> {
                        state.data?.movies?.let { movies ->
                            LazyColumn {
                                items(movies) { movie ->
                                    MovieItem(movie = movie)
                                }
                            }
                        }
                    }
                    is Response.Error -> {
                        Text("Error loading movies")
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = movie.name,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = movie.description,
                style = MaterialTheme.typography.body2,
                maxLines = 2
            )
            Text(
                text = "Rating: ${movie.voteAverage}",
                style = MaterialTheme.typography.caption
            )
        }
    }
}