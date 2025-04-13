package org.example.project.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Long,
    val name: String,
    val language: String="",
    val description: String="",
    val releaseDate: String="",
    val voteAverage: Double=0.0,
    val posterPath: String="",
)