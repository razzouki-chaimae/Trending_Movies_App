package com.chaimaerazzouki.trendingmovies.data.remote

import com.chaimaerazzouki.trendingmovies.domain.model.Configuration
import com.chaimaerazzouki.trendingmovies.domain.model.MovieDetails
import com.chaimaerazzouki.trendingmovies.domain.model.MovieListResponse
import com.chaimaerazzouki.trendingmovies.presentation.util.Constant.ACCESS_TOKEN
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("discover/movie")
    suspend fun getTrendingMovies(
        @Query("include_adult") includeAdult : Boolean = true,
        @Query("include_video") includeVideo : Boolean = true,
        @Query("language") language: String = "en-US",
        @Query("page") page : Int = 1,
        @Query("sort_by") sortBy : String = "popularity.desc",
        @Header("Authorization") accessToken : String = ACCESS_TOKEN
    ) : MovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId : Int,
        @Query("language") language: String = "en-US",
        @Header("Authorization") accessToken : String = ACCESS_TOKEN
    ) : MovieDetails

    @GET("configuration")
    suspend fun getImageConfiguration(
        @Header("Authorization") accessToken : String = ACCESS_TOKEN
    ) : Configuration
}