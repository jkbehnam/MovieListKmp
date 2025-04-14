package org.example.project.ui.movieDetails.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MovieOverview(overview: String?) {
    overview?.let {value->
        Text(
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
            text = value,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

/*@Composable
@Preview
private fun Preview(){
    MovieOverview("test test test test test test test test test test test")
}*/
