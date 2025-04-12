package org.example.project.data.api


import com.example.movieslist.data.model.MovieDetailsDto
import com.example.movieslist.data.model.MoviesListDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse

class TMDBApi(private val httpClient: HttpClient) {

    private val baseUrl = "https://api.themoviedb.org/3"

    /**
     * Fetch movie details by ID.
     * @param movieId The ID of the movie.
     * @return MovieDetailsDto object with movie details.
     */
    suspend fun getMovieDetails(movieId: Long): MovieDetailsDto {
        return httpClient.get {
            url("$baseUrl/movie/$movieId")
        }.body()
    }

    /**
     * Fetch a list of popular movies.
     * @param language The language of the movie list.
     * @param page The page number for pagination.
     * @return MoviesListDto object with the list of popular movies.
     */
    suspend fun getMovieList(language: String, page: Int): MoviesListDto {
        return httpClient.get {
            url("$baseUrl/movie/popular")
            parameter("language", language)
            parameter("page", page)
        }.body()


    }
}