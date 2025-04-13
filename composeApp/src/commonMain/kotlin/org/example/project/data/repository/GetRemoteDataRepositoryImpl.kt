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
            api.getMovieDetails(movieId = movieId)
        } catch (e: Exception) {
            null
        }
    }
}