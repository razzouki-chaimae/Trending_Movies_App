package com.chaimaerazzouki.trendingmovies.domain.repository

import arrow.core.Either
import com.chaimaerazzouki.trendingmovies.domain.model.ImageConfiguration
import com.chaimaerazzouki.trendingmovies.domain.model.MovieDetails
import com.chaimaerazzouki.trendingmovies.domain.model.Movie
import com.chaimaerazzouki.trendingmovies.domain.model.NetworkError

interface MovieRepository {

    suspend fun getTrendingMovies() : Either<NetworkError, List<Movie>>

    suspend fun getMovieDetails(movieId : Int) : Either<NetworkError, MovieDetails>

    suspend fun getImageConfiguration() : Either<NetworkError, ImageConfiguration>
}