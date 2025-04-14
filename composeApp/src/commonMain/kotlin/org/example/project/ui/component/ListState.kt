package org.example.project.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun  ListState(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    hasError: Boolean,
    onRetry: () -> Unit,

    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background).then(modifier),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .testTag("loadingIndicator")
                    .semantics {
                        contentDescription = "Loading"
                    }
            )
        } else if (hasError) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Error")
                TextButton(onClick = { onRetry() }) { Text("retry") }
            }
        }

    }

}

/*@Preview(name = "light", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun Preview() {
    ListState(isLoading = false, hasError = true) {

    }
}

@Preview(name = "dark", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewDark() {
    ListState(isLoading = false, hasError = true) {

    }
}*/

