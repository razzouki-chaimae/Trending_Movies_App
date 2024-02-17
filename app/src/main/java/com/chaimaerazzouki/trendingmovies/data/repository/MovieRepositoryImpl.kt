package com.chaimaerazzouki.trendingmovies.data.repository;

import arrow.core.Either
import com.chaimaerazzouki.trendingmovies.data.mapper.toNetworkError
import com.chaimaerazzouki.trendingmovies.data.remote.MoviesApi
import com.chaimaerazzouki.trendingmovies.domain.model.ImageConfiguration
import com.chaimaerazzouki.trendingmovies.domain.model.MovieDetails
import com.chaimaerazzouki.trendingmovies.domain.model.Movie
import com.chaimaerazzouki.trendingmovies.domain.model.NetworkError
import com.chaimaerazzouki.trendingmovies.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi
): MovieRepository{

    override suspend fun getTrendingMovies(): Either<NetworkError, List<Movie>> {
        return Either.catch {
            moviesApi.getTrendingMovies().results
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getMovieDetails(movieId : Int): Either<NetworkError, MovieDetails> {
        return Either.catch {
            moviesApi.getMovieDetails(movieId)
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getImageConfiguration(): Either<NetworkError, ImageConfiguration> {
        return Either.catch {
            moviesApi.getImageConfiguration().images
        }.mapLeft { it.toNetworkError() }
    }
}
