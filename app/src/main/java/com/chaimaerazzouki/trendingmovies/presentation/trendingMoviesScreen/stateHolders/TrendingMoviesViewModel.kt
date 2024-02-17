package com.chaimaerazzouki.trendingmovies.presentation.trendingMoviesScreen.stateHolders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaimaerazzouki.trendingmovies.domain.repository.MovieRepository
import com.chaimaerazzouki.trendingmovies.presentation.util.eventBus.Event
import com.chaimaerazzouki.trendingmovies.presentation.util.sendEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingMoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TrendingMoviesViewState())
    val state = _state.asStateFlow()

    init {
        getMovieImageConfiguration()
        getTrendingMovies()
    }

    fun getTrendingMovies(){
        viewModelScope.launch {

            _state.update {
                it.copy(isLoading = true)
            }

            movieRepository.getTrendingMovies()
                .onRight { movies ->
                    _state.update {
                        it.copy(movies = movies)
                    }
                }.onLeft { error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }

            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun getMovieImageConfiguration(){
        viewModelScope.launch {

            _state.update {
                it.copy(isLoading = true)
            }

            movieRepository.getImageConfiguration()
                .onRight { movieImageConfiguration ->
                    _state.update {
                        it.copy(movieImageConfiguration =  movieImageConfiguration)
                    }
                }.onLeft {error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }

            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}