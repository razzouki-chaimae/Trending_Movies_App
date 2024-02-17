package com.chaimaerazzouki.trendingmovies.dependencyInjection

import com.chaimaerazzouki.trendingmovies.data.repository.MovieRepositoryImpl
import com.chaimaerazzouki.trendingmovies.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductsRepository(impl : MovieRepositoryImpl) : MovieRepository
}