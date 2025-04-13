package org.example.project.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MoviesList(
    val page: Int?,
    val movies: List<Movie>?,
    val totalPages: Int?,
    val totalResults: Int?
)
