package com.chaimaerazzouki.trendingmovies.presentation.movieDetailsScreen.stateHolders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaimaerazzouki.trendingmovies.domain.repository.MovieRepository
import com.chaimaerazzouki.trendingmovies.presentation.util.eventBus.Event
import com.chaimaerazzouki.trendingmovies.presentation.util.sendEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailsViewState())
    val state = _state.asStateFlow()

    private val _movieId = MutableStateFlow<Int>(-1)
    val movieId: StateFlow<Int> = _movieId.asStateFlow()

    init {
        getMovieImageConfiguration()
        observeMovieIdChanges()
    }

    private fun observeMovieIdChanges() {
        viewModelScope.launch {
            _movieId.collect { movieId ->
                // Whenever movieId changes, fetch movie details
                movieId?.let {
                    getMovieDetails(it)
                }
            }
        }
    }

    fun setMovieId(movieId: Int) {
        _movieId.value = movieId
    }

    fun getMovieDetails(movieId : Int) {
        viewModelScope.launch {

            _state.update {
                it.copy(isLoading = true)
            }

            movieRepository.getMovieDetails(movieId = movieId)
                .onRight { movie ->
                    _state.update {
                        it.copy(movie = movie)
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