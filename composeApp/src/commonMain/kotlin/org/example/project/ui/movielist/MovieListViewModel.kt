package org.example.project.ui.movielist

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.example.project.common.AppEvent
import org.example.project.domain.model.Movie
import org.example.project.domain.model.Response
import org.example.project.domain.usecase.GetPopularMovieListUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MovieListViewModel :
    ViewModel(), KoinComponent {

        private val getPopularMovieListUseCase: GetPopularMovieListUseCase by inject()

    private val _state = MutableStateFlow(ScreenState())
    val state: StateFlow<ScreenState> = _state


    fun getMovies(page: Int=_state.value.currentPage) {
        if (state.value.movies != null || !state.value.isLoading) {
            getPopularMovieListUseCase(page).onEach { result ->
                when (result) {
                    is Response.Success -> {
                        val movies: List<Movie> = state.value.movies.orEmpty() + (result.data?.movies.orEmpty())
                        _state.value = state.value.copy(
                            movies = movies,
                            isLoading = false,
                            hasError = false,
                            currentPage = result.data?.page ?: 1
                        )
                    }

                    is Response.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true,
                            hasError = false
                        )
                    }

                    is Response.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            hasError = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun onEvent(event: ScreenEvent) {
        if (event is ScreenEvent.OnItemClicked) {
            _state.value = state.value.copy(
                navigationEvent = AppEvent(event.id)
            )
        }
        if (event is ScreenEvent.OnRetryClicked){
            if (event.page==null){
            _state.value=state.value.copy(
                firstTimeListLoadEvent = AppEvent(Any())
            )}else{

                getMovies(page =_state.value.currentPage+1 )
            }

        }
        if (event is ScreenEvent.OnLoadNextPage){
            getMovies(_state.value.currentPage+1)

        }
    }

    data class ScreenState(
        val currentPage: Int = 1,
        val isLoading: Boolean = false,
        val hasError: Boolean = false,
        val movies: List<Movie>? = emptyList(),
        val lazyListState: LazyListState = LazyListState(),
        val firstTimeListLoadEvent: AppEvent<Any> = AppEvent(Any()),
        val navigationEvent: AppEvent<Long?>? = null
    )

    sealed class ScreenEvent {
        data class OnItemClicked(val id: Long?) : ScreenEvent()
        data class OnRetryClicked(val page:Int?=null) : ScreenEvent()
        data object OnLoadNextPage : ScreenEvent()
    }
}



