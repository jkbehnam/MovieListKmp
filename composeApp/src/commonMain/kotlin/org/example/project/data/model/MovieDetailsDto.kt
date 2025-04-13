package org.example.project.data.model

import org.example.project.domain.model.Movie
import org.example.project.domain.model.MoviesList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.project.data.api.model.Genre
import org.example.project.data.api.model.MoviesListDto
import org.example.project.data.api.model.ProductionCompany
import org.example.project.data.api.model.ProductionCountry
import org.example.project.data.api.model.SpokenLanguage

@Serializable
data class MovieDetailsDto(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    @SerialName("imdb_id")
    val imdbId: String,
    @SerialName("origin_country")
    val originCountry: List<String>,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @SerialName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)

fun MoviesListDto?.convertMovieListDto(): MoviesList {
    val movies = this?.movieDto?.map { it ->
        Movie(
            id = it.id,
            name = it.title,
            language = it.originalLanguage,
            description = it.overview,
            releaseDate = it.releaseDate,
            voteAverage = it.voteAverage,
            posterPath = "https://image.tmdb.org/t/p/original"+it.posterPath
        )
    }
    val movieList = MoviesList(
        page = this?.page,
        movies = movies,
        totalPages = this?.totalPages,
        totalResults = this?.totalResults
    )

    return movieList

}