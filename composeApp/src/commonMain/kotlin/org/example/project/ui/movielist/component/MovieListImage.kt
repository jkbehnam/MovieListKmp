package org.example.project.ui.movielist.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import movielistkmp.composeapp.generated.resources.Res
import movielistkmp.composeapp.generated.resources.ic_error
import movielistkmp.composeapp.generated.resources.ic_image
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieImageList(
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    path: String,
    modifier: Modifier=Modifier
) {

    var isLoading by remember { mutableStateOf(true) }

    Box(modifier = modifier) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        with(sharedTransitionScope) {
            AsyncImage(
                model = path,
                contentDescription = "Movie Poster",
                placeholder = painterResource(Res.drawable.ic_image),
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(
                            key = path
                        ),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)
                        }
                    )
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                error = painterResource(Res.drawable.ic_error),
                onLoading = { isLoading = true },
                onError = { isLoading = false },
                onSuccess = {
                    isLoading = false
                }
            )
        }

    }
}

/*@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Preview
private fun Preview() {
    MoviesListTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                MovieImageList(
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    path = ""
                )
            }
        }
    }
}*/
