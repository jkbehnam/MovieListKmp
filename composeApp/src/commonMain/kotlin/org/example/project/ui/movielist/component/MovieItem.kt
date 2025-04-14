package org.example.project.ui.movielist.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.ui.component.Chip
import org.example.project.domain.model.Movie

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieItem(
    animatedVisibilityScope: AnimatedVisibilityScope?,
    sharedTransitionScope: SharedTransitionScope?,
    movie: Movie,
    onclick: (id: Long) -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (isPressed) 0.9f else 1f, label = "")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(onPress = {
                    isPressed = true
                    tryAwaitRelease()
                    isPressed = false
                }, onTap = { onclick(movie.id) })

            }
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            MovieImageList(
                animatedVisibilityScope = animatedVisibilityScope!!,
                sharedTransitionScope = sharedTransitionScope!!,
                path = movie.posterPath
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .height(100.dp)
                    .align(Alignment.CenterVertically),
                Arrangement.SpaceBetween

            ) {
                Text(
                    text = movie.name,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )

                Column(Modifier.fillMaxWidth()) {
                    Chip("Lang: ${movie.language}", null)

                    Spacer(Modifier.height(5.dp))

                    Row {
                        Chip(text = movie.releaseDate.substringBefore("-"), img = null)
                        Spacer(Modifier.width(5.dp))
                        Chip(movie.voteAverage.toString(), Icons.Default.Favorite)

                    }
                }

            }
        }

    }

}


/*
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Preview
fun MovieItemPreview() {
    val jsonString = LocalContext.current.assets.open("mock_movie_list.json").bufferedReader()
        .use { it.readText() }

    val movieListDto = Gson().fromJson(jsonString, MoviesListDto::class.java)
    MoviesListTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                MovieItem(
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    movie = movieListDto.convertMovieListDto().movies!![0]
                ) { }
            }
        }
    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(
    name = "Dark Theme",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewDark() {
    val jsonString = LocalContext.current.assets.open("mock_movie_list.json").bufferedReader()
        .use { it.readText() }

    val movieListDto = Gson().fromJson(jsonString, MoviesListDto::class.java)
    MoviesListTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                MovieItem(
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    movie = movieListDto.convertMovieListDto().movies!![0]
                ) { }
            }
        }
    }
}*/
