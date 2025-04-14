package org.example.project.data.repository

import org.example.project.domain.repository.GetRemoteDataRepository
import org.example.project.data.api.TMDBApi
import org.example.project.data.model.MovieDetailsDto
import org.example.project.data.model.convertMovieListDto
import org.example.project.domain.model.MoviesList

class GetRemoteDataRepositoryImpl(
    private val api: TMDBApi
) : GetRemoteDataRepository {

    override suspend fun getMovieList(page: Int): MoviesList {
        val movieDto = api.getMovieList("en", page)
        return movieDto.convertMovieListDto()
    }

    override suspend fun getMovieDetail(movieId: Long): MovieDetailsDto? {
        return try {
            println("Attempting to fetch movie details for ID: $movieId")
            api.getMovieDetails(movieId = movieId)
        } catch (e: Exception) {
            println("Error fetching movie details: ${e.message}")
            e.printStackTrace()
            null
        }
    }
}