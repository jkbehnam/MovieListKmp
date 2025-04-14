package org.example.project.ui.movielist

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.ui.component.ListState
import org.example.project.ui.movielist.component.MovieItem
import org.example.project.ui.movielist.MovieListViewModel.ScreenState
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieListScreen(
    animatedVisibilityScope: AnimatedVisibilityScope? = null,
    sharedTransitionScope: SharedTransitionScope?= null,
    movieListViewModel: MovieListViewModel = koinViewModel(),
    navigateToDetail: (Long) -> Unit
) {
    val screenState = movieListViewModel.state.collectAsState().value

    LaunchedEffect(screenState) {
        val firstTimeListEvent = screenState.firstTimeListLoadEvent.getContentIfNotHandled()
        if (firstTimeListEvent != null) {
            movieListViewModel.getMovies()
        }

        val navigationEvent = screenState.navigationEvent?.getContentIfNotHandled()
        if (navigationEvent != null) {
            navigateToDetail.invoke(navigationEvent)
        }
    }

    MoviesListContentSection(
        animatedVisibilityScope = animatedVisibilityScope,
        sharedTransitionScope = sharedTransitionScope,
        state = screenState,
        onEvent = { event ->
            movieListViewModel.onEvent(event = event)
        }
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MoviesListContentSection(
    animatedVisibilityScope: AnimatedVisibilityScope?,
    sharedTransitionScope: SharedTransitionScope?,
    state: ScreenState,
    onEvent: (MovieListViewModel.ScreenEvent) -> Unit
) {

    Box(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        MoviesLazyList(
            animatedVisibilityScope = animatedVisibilityScope,
            sharedTransitionScope = sharedTransitionScope,
            state = state,
            onEvent = onEvent
        )



    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MoviesLazyList(
    animatedVisibilityScope: AnimatedVisibilityScope?,
    sharedTransitionScope: SharedTransitionScope?,
    state: ScreenState,
    onEvent: (MovieListViewModel.ScreenEvent) -> Unit
) {
    val listState = state.lazyListState
    val movies = state.movies ?: listOf()

    LazyColumn(state = listState, horizontalAlignment = Alignment.CenterHorizontally) {

        items(items = movies) { item ->
            MovieItem(
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope,
                movie = item
            ) { id ->
                onEvent(MovieListViewModel.ScreenEvent.OnItemClicked(id))
            }

        }


            item {
                ListState(modifier = Modifier.padding(horizontal = 16.dp), isLoading=state.isLoading,hasError=state.hasError){
                    onEvent(MovieListViewModel.ScreenEvent.OnRetryClicked(state.currentPage))
                }
            }

    }

    LaunchedEffect(state.movies) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleIndex ->
                if (lastVisibleIndex == movies.size - 1) {
                    onEvent(MovieListViewModel.ScreenEvent.OnLoadNextPage)
                }
            }
    }

}

/*
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(
    name = "Dark Theme",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun Preview1() {
    val jsonString = LocalContext.current.assets.open("mock_movie_list.json").bufferedReader()
        .use { it.readText() }
    val movieListDto = Gson().fromJson(jsonString, MoviesListDto::class.java)
    // val movieListDto = Json.decodeFromString<MoviesListDto>(jsonString)
    MoviesListTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                MoviesListContentSection(
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    ScreenState(
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
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(
    name = "Dark Theme",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun Preview2() {
    val jsonString = LocalContext.current.assets.open("mock_movie_list.json").bufferedReader()
        .use { it.readText() }
    val movieListDto = Gson().fromJson(jsonString, MoviesListDto::class.java)
    // val movieListDto = Json.decodeFromString<MoviesListDto>(jsonString)
    MoviesListTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                MoviesListContentSection(
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    ScreenState(
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


*/
