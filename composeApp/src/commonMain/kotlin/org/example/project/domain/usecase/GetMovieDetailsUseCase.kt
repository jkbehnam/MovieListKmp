package org.example.project.domain.usecase

import org.example.project.data.model.MovieDetailsDto
import org.example.project.domain.model.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.project.domain.repository.GetRemoteDataRepository

class GetMovieDetailsUseCase(private val repository: GetRemoteDataRepository) {
    operator fun invoke(movieId:Long): Flow<Response<MovieDetailsDto?>> =flow{
        try {
            emit(Response.Loading())
           val details= repository.getMovieDetail(movieId)
            emit(Response.Success(details))
        }catch (_:Exception){
            emit(Response.Error())
        }
    }
}
