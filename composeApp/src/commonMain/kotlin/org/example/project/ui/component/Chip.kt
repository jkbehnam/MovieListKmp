package org.example.project.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun Chip(text: String, img: ImageVector?) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .background(color = Color.Gray)
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        img?.let {
            Icon(
                modifier = Modifier.size(15.dp),
                imageVector = it,
                contentDescription = "icon",
                tint = Color.White
            )
            Spacer(Modifier.width(5.dp))

        }

        Text(text = text, style = MaterialTheme.typography.caption, color = Color.White)
    }
}
