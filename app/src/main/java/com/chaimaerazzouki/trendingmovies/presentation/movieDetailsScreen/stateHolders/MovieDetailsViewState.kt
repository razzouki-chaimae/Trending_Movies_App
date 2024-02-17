package com.chaimaerazzouki.trendingmovies.presentation.movieDetailsScreen.stateHolders

import com.chaimaerazzouki.trendingmovies.domain.model.ImageConfiguration
import com.chaimaerazzouki.trendingmovies.domain.model.MovieDetails

data class MovieDetailsViewState(
    val isLoading: Boolean = false,
    val movie: MovieDetails? = null,
    val movieImageConfiguration: ImageConfiguration? = null,
    val error: String? = null
)