package id.ak.movieshighlight.watchlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.EntryPointAccessors
import id.ak.movieshighlight.MainRoute
import id.ak.movieshighlight.WatchlistDependencies
import id.ak.movieshighlight.domain.entity.Watchlist
import id.ak.movieshighlight.ui.theme.MoviesHighlightTheme
import id.ak.movieshighlight.watchlist.details.DetailsScreen
import id.ak.movieshighlight.watchlist.list.WatchlistScreen
import javax.inject.Inject

class WatchlistActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: WatchlistViewModel.Factory

    private val viewModel by viewModels<WatchlistViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerWatchlistComponent.builder()
            .dependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    WatchlistDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MoviesHighlightTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = WatchlistRoute.List) {
                    composable<WatchlistRoute.List> {
                        WatchlistScreen(
                            viewModel = viewModel,
                            onNavigateUp = { finish() },
                            openDetails = {
                                val route = when (it.type) {
                                    Watchlist.Type.Movie -> WatchlistRoute.Details(movieId = it.id)
                                    Watchlist.Type.TvSerial -> WatchlistRoute.Details(tvSerialId = it.id)
                                }
                                navController.navigate(route)
                            }
                        )
                    }
                    composable<WatchlistRoute.Details> {
                        MoviesHighlightTheme(darkTheme = true) {
                            val details = it.toRoute<MainRoute.Details>()
                            val movieId = details.movieId
                            val tvSerialId = details.tvSerialId

                            LaunchedEffect(Unit) {
                                when {
                                    movieId != null -> viewModel.fetchMovieDetails(movieId)
                                    tvSerialId != null -> viewModel.fetchTvSerialDetails(tvSerialId)
                                }
                            }

                            DetailsScreen(
                                viewModel = viewModel,
                                onNavigateUp = navController::popBackStack,
                                onRetry = {
                                    when {
                                        movieId != null -> {
                                            viewModel.fetchMovieDetails(movieId)
                                        }

                                        tvSerialId != null -> {
                                            viewModel.fetchTvSerialDetails(tvSerialId)
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}