package org.example.project.data.api.model
import kotlinx.serialization.Serializable
 @Serializable
data class Genre(
    val id: Int,
    val name: String
)