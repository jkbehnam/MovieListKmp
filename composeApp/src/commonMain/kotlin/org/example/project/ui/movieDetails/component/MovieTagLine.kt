package org.example.project.ui.movieDetails.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MovieTagLine(tagLing: String?) {
    Text(
        text = tagLing.orEmpty(),
        modifier = Modifier.padding(vertical = 5.dp),
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.secondary
    )

}

/*
@Composable
@Preview
fun MovieTagLinePreview(){
    MovieTitle("Inception")
}*/
