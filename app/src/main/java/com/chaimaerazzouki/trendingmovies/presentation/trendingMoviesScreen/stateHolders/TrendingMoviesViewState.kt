package com.chaimaerazzouki.trendingmovies.presentation.trendingMoviesScreen.stateHolders

import com.chaimaerazzouki.trendingmovies.domain.model.ImageConfiguration
import com.chaimaerazzouki.trendingmovies.domain.model.Movie

data class TrendingMoviesViewState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val movieImageConfiguration: ImageConfiguration? = null,
    val error: String? = null
)
