package org.example.project.ui.movieDetails.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import movielistkmp.composeapp.generated.resources.Res
import movielistkmp.composeapp.generated.resources.ic_error
import org.jetbrains.compose.resources.painterResource


@Composable
fun MoviePoster(imageUrl: String) {

    var isLoading by remember { mutableStateOf(true) }
    val colorStopped = arrayOf(
        0.0f to Color.Transparent,
        1f to MaterialTheme.colors.background
    )

    Box {
        if (isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        AsyncImage(
        model = imageUrl,
        contentDescription = "repository images",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
       // placeholder = painterResource(R.drawable.ic_progress),
        error = painterResource(Res.drawable.ic_error),
        onLoading = {isLoading=true},
        onError = {isLoading=false},
            onSuccess = {
                isLoading=false
            }
    )
    Spacer(modifier =Modifier.height(100.dp).fillMaxWidth().align(Alignment.BottomCenter).background(
        Brush.verticalGradient(colorStops = colorStopped)) )
    }

}
/*

@Preview
@Composable
fun MoviePosterPreview() {
    MoviePoster("https://api2.zoomit.ir/media/2020-3-a358ad4f-defd-455b-b35b-cedad50cd466-638baeeb8f0b9a6b86bbe0bd?w=384&q=75")
}*/
