package org.example.project.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.data.api.TMDBApi
import org.example.project.data.repository.GetRemoteDataRepositoryImpl
import org.example.project.domain.repository.GetRemoteDataRepository
import org.example.project.domain.usecase.GetMovieDetailsUseCase
import org.example.project.domain.usecase.GetPopularMovieListUseCase
import org.example.project.ui.movieDetails.MovieDetailsViewModel
import org.example.project.ui.movielist.MovieListViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

fun appModule(engine: HttpClientEngine): Module = module {
    single {
        HttpClient(engine) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("HTTP Client: $message")
                    }
                }
                level = LogLevel.ALL
            }
            defaultRequest {
                header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNjEwMzM2OTZhNzIxNmEwYmFmZWE0MzBkMzgwZThlMiIsIm5iZiI6MTczMTgyNzMwNC43MzQyMzEyLCJzdWIiOiI2NTg4NDY5MzQ3NzIxNTVhMGI0M2IxMjQiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.s5Ym7emqzCyGKZ98Gjff3fVJP3MoWJ6ICdIpaJEz3Ao")
                header("accept", "application/json")
            }
        }
    }
    
    single { TMDBApi(get()) }
    
    single<GetRemoteDataRepository> { GetRemoteDataRepositoryImpl(get()) }
    
    single<GetPopularMovieListUseCase> { GetPopularMovieListUseCase(get<GetRemoteDataRepository>()) }
    single<GetMovieDetailsUseCase> { GetMovieDetailsUseCase(get<GetRemoteDataRepository>()) }

   viewModel { MovieListViewModel() }
   viewModel { MovieDetailsViewModel() }
}
