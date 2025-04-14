package org.example.project.ui.movieDetails

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.ui.component.ErrorBox
import org.example.project.ui.component.LoadingDialog
import org.example.project.ui.movieDetails.component.MovieGenres
import org.example.project.ui.movieDetails.component.MovieOverview
import org.example.project.ui.movieDetails.component.MoviePoster
import org.example.project.ui.movieDetails.component.MovieTagLine
import org.example.project.ui.movieDetails.component.MovieTitle
import org.example.project.ui.movielist.component.MovieImageList
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieDetailsScreen(
    animatedVisibilityScope: AnimatedVisibilityScope?,
    sharedTransitionScope: SharedTransitionScope?,
    movieId: Long,
    movieDetailsViewModel: MovieDetailsViewModel = koinViewModel()
) {

    val screenState = movieDetailsViewModel.state.collectAsState().value

    LaunchedEffect(screenState) {
        val getDetails = screenState.firstTimeListLoadEvent.getContentIfNotHandled()
        if (getDetails != null) {
            movieDetailsViewModel.getMovieDetails(movieId)
        }
    }

    MainMovieDetailSection(
        animatedVisibilityScope = animatedVisibilityScope,
        sharedTransitionScope = sharedTransitionScope,
        screenState = screenState,
        ) { event ->
        movieDetailsViewModel.onEvent(
            event
        )
    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun MainMovieDetailSection(
    animatedVisibilityScope: AnimatedVisibilityScope?,
    sharedTransitionScope: SharedTransitionScope?,
    screenState: MovieDetailsViewModel.ScreenState,
    onEvent: (MovieDetailsViewModel.ScreenEvent) -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {

        if (screenState.isLoading) {
            LoadingDialog()
        } else if (screenState.hasError) {
            ErrorBox(modifier = Modifier.align(Alignment.Center)) {
                onEvent(MovieDetailsViewModel.ScreenEvent.OnRetryClicked)

            }
        } else {
            MovieDetailsContentSection(
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope,
                screenState = screenState
            )

        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieDetailsContentSection(
    animatedVisibilityScope: AnimatedVisibilityScope?,
    sharedTransitionScope: SharedTransitionScope?,
    screenState: MovieDetailsViewModel.ScreenState
) {

    val movieDetails = screenState.movieDetails
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        movieDetails?.let { details ->
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Box {
                    MoviePoster(imageUrl = "https://image.tmdb.org/t/p/original" + details.backdropPath)
                    MovieImageList(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .align(Alignment.BottomStart),
                        animatedVisibilityScope = animatedVisibilityScope!!,
                        sharedTransitionScope = sharedTransitionScope!!,
                        path = "https://image.tmdb.org/t/p/original" + details.posterPath
                    )
                }

                Column(Modifier.padding(horizontal = 16.dp)) {
                    MovieTitle(title = details.title)
                    MovieTagLine(tagLing = details.tagline)
                    MovieGenres(genres = details.genres.map { it.name })
                    MovieOverview(overview = details.overview)
                }

            }
        }
    }
}


/*
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

    val movieDetails = Gson().fromJson(jsonString, MovieDetailsDto::class.java)
    MoviesListTheme {
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

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(
    name = "Light Theme",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun Preview2() {
    val jsonString = LocalContext.current.assets.open("mock_movie_details.json").bufferedReader()
        .use { it.readText() }

    val movieDetails = Gson().fromJson(jsonString, MovieDetailsDto::class.java)
    MoviesListTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {

                MainMovieDetailSection(
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    screenState = MovieDetailsViewModel.ScreenState(
                        isLoading = false,
                        hasError = true,
                        movieDetails = movieDetails
                    )
                ){}
            }
        }
    }
}
*/

