package org.example.project.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.project.data.api.model.MovieDto

@Serializable
data class MoviesListDto(
    val page: Int,
    @SerialName("results")
    val movieDto: List<MovieDto>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)