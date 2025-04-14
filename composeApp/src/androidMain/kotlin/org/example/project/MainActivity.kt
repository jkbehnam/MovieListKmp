package org.example.project

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.ui.movieDetails.MovieDetailsContentSection
import org.example.project.data.model.MoviesListDto
import org.example.project.theme.Theme
import org.example.project.ui.movielist.MovieListViewModel
import org.example.project.ui.movielist.MoviesListContentSection
import kotlinx.serialization.json.Json
import org.example.project.data.model.MovieDetailsDto
import org.example.project.data.model.convertMovieListDto
import org.example.project.ui.movieDetails.MovieDetailsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun Preview2() {
    val jsonString = LocalContext.current.assets.open("mock_movie_list.json").bufferedReader()
        .use { it.readText() }

    val movie = Json { ignoreUnknownKeys = true }
    val movieListDto = movie.decodeFromString<MoviesListDto>(jsonString)
    Theme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                MoviesListContentSection(
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    MovieListViewModel.ScreenState(
                        currentPage = 1,
                        isLoading = false,
                        hasError = false,
                        movies = movieListDto.convertMovieListDto().movies
                    )
                ) { }
            }
        }
    }
}

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(
    name = "Dark Theme",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun Preview() {
    val jsonString = LocalContext.current.assets.open("mock_movie_details.json").bufferedReader()
        .use { it.readText() }

    val movieDetails = Json.decodeFromString<MovieDetailsDto>(jsonString)
    Theme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {

                MovieDetailsContentSection(

                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    MovieDetailsViewModel.ScreenState(
                        isLoading = false,
                        hasError = false,
                        movieDetails = movieDetails
                    )
                )
            }
        }
    }
}