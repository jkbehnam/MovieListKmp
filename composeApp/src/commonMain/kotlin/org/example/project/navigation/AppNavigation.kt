package org.example.project.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.example.project.ui.movieDetails.MovieDetailsScreen
import kotlinx.serialization.Serializable
import org.example.project.ui.movielist.MovieListScreen

@Serializable
object List

@Serializable
data class Detail(val id: Long)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation(navController: NavHostController) {
    SharedTransitionLayout {


    NavHost(
        navController = navController,
        startDestination = List
    ) {

        composable<List> {
            MovieListScreen(
                animatedVisibilityScope = this,
                sharedTransitionScope = this@SharedTransitionLayout,
                navigateToDetail = { id ->
                    navController.navigate(Detail(id))
                }
            )
        }
        composable<Detail> { backStackEntry ->
            val detail: Detail = backStackEntry.toRoute()
            MovieDetailsScreen(
                animatedVisibilityScope = this,
                sharedTransitionScope = this@SharedTransitionLayout,
                movieId = detail.id,
            )
        }

    }}
}