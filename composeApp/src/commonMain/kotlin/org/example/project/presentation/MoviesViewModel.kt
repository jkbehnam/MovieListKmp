package org.example.project.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.example.project.domain.model.MoviesList
import org.example.project.domain.model.Response
import org.example.project.domain.usecase.GetPopularMovieListUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel

class MoviesViewModel : ViewModel(), KoinComponent {
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase by inject()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    var moviesState by mutableStateOf<Response<MoviesList?>>(Response.Loading())
        private set

    fun getMovies() {
        coroutineScope.launch {
            getPopularMovieListUseCase(1).collect { response ->
                moviesState = response
            }
        }
    }
}