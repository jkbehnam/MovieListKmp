package org.example.project.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import org.example.project.data.model.MovieDetailsDto
import org.example.project.data.api.model.MoviesListDto

class TMDBApi(private val httpClient: HttpClient) {
    private val baseUrl = "https://api.themoviedb.org/3"

    suspend fun getMovieDetails(movieId: Long): MovieDetailsDto {
        return httpClient.get {
            url("$baseUrl/movie/$movieId")
        }.body()
    }

    suspend fun getMovieList(language: String, page: Int): MoviesListDto {
        return httpClient.get {
            url("$baseUrl/movie/popular")
            parameter("language", language)
            parameter("page", page)
        }.body()
    }
}