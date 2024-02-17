package com.chaimaerazzouki.trendingmovies.data.mapper

import retrofit2.HttpException
import com.chaimaerazzouki.trendingmovies.domain.model.ApiError
import com.chaimaerazzouki.trendingmovies.domain.model.NetworkError
import java.io.IOException

fun Throwable.toNetworkError() : NetworkError{
    val error = when(this){
        is IOException -> ApiError.NetworkError
        is HttpException -> ApiError.UnknownResponse
        else -> ApiError.UnknownError
    }
    return NetworkError(error = error, throwable = this)
}