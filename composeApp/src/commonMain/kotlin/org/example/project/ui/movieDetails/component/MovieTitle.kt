package org.example.project.ui.movieDetails.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun MovieTitle(title: String?) {
    Text(
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.onBackground,
        text = title.orEmpty(),

    )
}

/*
@Composable
@Preview
private fun Preview(){
    MovieTitle("Inception")
}*/
