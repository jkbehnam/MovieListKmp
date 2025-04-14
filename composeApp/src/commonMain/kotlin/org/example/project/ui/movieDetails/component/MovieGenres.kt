package org.example.project.ui.movieDetails.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import org.example.project.ui.component.Chip

@Composable
fun MovieGenres(genres: List<String>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(genres) { genre ->
            Chip(text = genre, null)
        }
    }
}

/*
@Preview
@Composable
private fun Preview() {
    MovieGenres(listOf("Drama", "Horror"))
}*/
