package org.example.project.domain.usecase

import org.example.project.domain.model.MoviesList
import org.example.project.domain.model.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.project.domain.repository.GetRemoteDataRepository

class GetPopularMovieListUseCase(private val repository: GetRemoteDataRepository) {
    operator fun invoke(page: Int): Flow<Response<MoviesList?>> = flow {
        try {
            emit(Response.Loading())
            val result = repository.getMovieList(page = page)
            emit(Response.Success(result))

        } catch (_: Exception) {
            emit(Response.Error())
        }
    }
}
