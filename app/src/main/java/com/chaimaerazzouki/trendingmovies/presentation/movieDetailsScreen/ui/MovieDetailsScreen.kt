package com.chaimaerazzouki.trendingmovies.presentation.movieDetailsScreen.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chaimaerazzouki.trendingmovies.presentation.movieDetailsScreen.stateHolders.MovieDetailsViewModel
import com.chaimaerazzouki.trendingmovies.presentation.movieDetailsScreen.stateHolders.MovieDetailsViewState
import com.chaimaerazzouki.trendingmovies.presentation.util.components.LoadingDialog
import com.chaimaerazzouki.trendingmovies.presentation.util.components.MyTopAppBar

@Composable
internal fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    movieId: Int) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    viewModel.setMovieId(movieId)
    MovieDetailsScreenContent(state = state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreenContent(state: MovieDetailsViewState) {
    LoadingDialog(isLoading = state.isLoading)
    state.movie?.let { it1 ->
        val movieGenres = it1.genres.joinToString { it.name }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { MyTopAppBar(title = it1.title)  }
        ){
            LazyColumn(
                modifier = Modifier
                .padding(top = it.calculateTopPadding())
            ){
                item {
                    if (state.movieImageConfiguration != null) {
                        val imageUrl =
                            state.movieImageConfiguration.secure_base_url + state.movieImageConfiguration.poster_sizes[0] + "/" + it1.poster_path
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(imageUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(0.7f),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = it1.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = movieGenres,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = it1.release_date,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(
                        text = "Overview",
                        style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = it1.overview,
                        style = MaterialTheme.typography.titleSmall)
                }
            }
        }
    }

}