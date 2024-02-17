package com.chaimaerazzouki.trendingmovies.presentation.trendingMoviesScreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.chaimaerazzouki.trendingmovies.presentation.trendingMoviesScreen.stateHolders.TrendingMoviesViewModel
import com.chaimaerazzouki.trendingmovies.presentation.trendingMoviesScreen.stateHolders.TrendingMoviesViewState
import com.chaimaerazzouki.trendingmovies.presentation.trendingMoviesScreen.ui.components.MovieCard
import com.chaimaerazzouki.trendingmovies.presentation.util.components.LoadingDialog
import com.chaimaerazzouki.trendingmovies.presentation.util.components.MyTopAppBar

@Composable
internal fun TrendingMoviesScreen(
    viewModel: TrendingMoviesViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    TrendingMoviesScreenContent(state = state, navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendingMoviesScreenContent(
    state: TrendingMoviesViewState,
    navController: NavHostController
) {
    LoadingDialog(isLoading = state.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MyTopAppBar(title = "Trending Movies")}
    ) {
        LazyVerticalStaggeredGrid(
            modifier = Modifier.padding(top = it.calculateTopPadding()).padding(5.dp),
            columns = StaggeredGridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp) {
            items(state.movies) {movie ->
                MovieCard(movie = movie, movieImageConfiguration = state.movieImageConfiguration, navController = navController)
            }
        }
    }
}