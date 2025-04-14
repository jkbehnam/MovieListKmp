package org.example.project.ui.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.example.project.common.AppEvent
import org.example.project.data.model.MovieDetailsDto
import org.example.project.domain.model.Response
import org.example.project.domain.usecase.GetMovieDetailsUseCase
import org.koin.core.component.KoinComponent

class MovieDetailsViewModel(private val getMovieDetailsUseCase: GetMovieDetailsUseCase): ViewModel(), KoinComponent {
    private val _state = MutableStateFlow(ScreenState())
    val state: StateFlow<ScreenState> = _state

    fun getMovieDetails(movieId: Long) {

        getMovieDetailsUseCase.invoke(movieId).onEach { result ->
            when (result) {
                is Response.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        hasError = true
                    )
                }

                is Response.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true,
                        hasError = false
                    )
                }

                is Response.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        hasError = false,
                        movieDetails = result.data
                    )
                }
            }

        }.launchIn(viewModelScope)
    }

    sealed class ScreenEvent {
        data object OnRetryClicked : ScreenEvent()
    }

    fun onEvent(event: ScreenEvent){
        when(event){
            ScreenEvent.OnRetryClicked -> {
                _state.value=_state.value.copy(
                    firstTimeListLoadEvent = AppEvent(Any())
                )
            }
        }
    }

    data class ScreenState(
        val isLoading: Boolean = false,
        val hasError: Boolean = false,
        val movieDetails: MovieDetailsDto? = null,
        val firstTimeListLoadEvent: AppEvent<Any> = AppEvent(Any()),
    )
}