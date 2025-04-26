package id.ak.movieshighlight

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import id.ak.movieshighlight.details.DetailsScreen
import id.ak.movieshighlight.home.HomeScreen
import id.ak.movieshighlight.ui.theme.MoviesHighlightTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesHighlightTheme {
                val navController = rememberNavController()

                RequestNotificationPermission()

                NavHost(navController, MainRoute.Home) {
                    composable<MainRoute.Home> {
                        HomeScreen(
                            openMovieDetails = {
                                navController.navigate(MainRoute.Details(movieId = it))
                            },
                            openTvSerialDetails = {
                                navController.navigate(MainRoute.Details(tvSerialId = it))
                            },
                            openWatchlist = {
                                Intent(
                                    Intent.ACTION_VIEW,
                                    "movies://watchlist".toUri()
                                ).also { intent ->
                                    startActivity(intent)
                                }
                            }
                        )
                    }
                    composable<MainRoute.Details> {
                        MoviesHighlightTheme(darkTheme = true) {
                            val details = it.toRoute<MainRoute.Details>()

                            DetailsScreen(
                                movieId = details.movieId,
                                tvSerialId = details.tvSerialId,
                                onNavigateUp = navController::popBackStack
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun RequestNotificationPermission() {
        val context = LocalContext.current
        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = {}
        )

        LaunchedEffect(Unit) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }
}