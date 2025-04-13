package org.example.project.domain.repository

import org.example.project.data.model.MovieDetailsDto
import org.example.project.domain.model.MoviesList

interface GetRemoteDataRepository {
    suspend fun getMovieList(page:Int): MoviesList
    suspend fun getMovieDetail(movieId:Long): MovieDetailsDto?
}