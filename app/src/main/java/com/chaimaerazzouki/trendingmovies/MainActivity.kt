package com.chaimaerazzouki.trendingmovies

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.chaimaerazzouki.trendingmovies.presentation.movieDetailsScreen.ui.MovieDetailsScreen
import com.chaimaerazzouki.trendingmovies.presentation.trendingMoviesScreen.ui.TrendingMoviesScreen
import com.chaimaerazzouki.trendingmovies.presentation.util.eventBus.Event
import com.chaimaerazzouki.trendingmovies.presentation.util.eventBus.EventBus
import com.chaimaerazzouki.trendingmovies.ui.theme.TrendingMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrendingMoviesTheme {
                val lifecycle = androidx.compose.ui.platform.LocalLifecycleOwner.current.lifecycle
                LaunchedEffect(key1 = lifecycle) {
                    repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                        EventBus.events.collect{ event ->
                            if (event is Event.Toast) {
                                Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "trendingMoviesScreen"
        ) {
            composable("trendingMoviesScreen") {
                TrendingMoviesScreen(navController = navController)
            }
            composable(
                "movieDetailsScreen/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId")
                if (movieId != null) {
                    MovieDetailsScreen(movieId = movieId)
                }
            }
        }
    }
}