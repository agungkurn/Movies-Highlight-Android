package id.ak.movieshighlight.watchlist.details

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import id.ak.convention.features.details.R
import id.ak.movieshighlight.ui.state.UiState
import id.ak.movieshighlight.watchlist.WatchlistViewModel
import id.ak.movieshighlight.watchlist.sheet.MovieSheet
import id.ak.movieshighlight.watchlist.sheet.TvSerialSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewModel: WatchlistViewModel,
    onNavigateUp: () -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val movieDetails by viewModel.movieDetails.collectAsStateWithLifecycle()
    val tvSerialDetails by viewModel.tvSerialDetails.collectAsStateWithLifecycle()
    val isInWatchlist by viewModel.isInWatchlist.collectAsStateWithLifecycle(null)
    val loadingWatchlist by viewModel.loadingWatchlist.collectAsStateWithLifecycle()

    val actionRetry = stringResource(id.ak.convention.common.ui.R.string.action_retry)

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val snackbarHostState = remember { SnackbarHostState() }



    LaunchedEffect(uiState) {
        if (uiState is UiState.Error) {
            snackbarHostState.currentSnackbarData?.dismiss()
            (uiState as UiState.Error).message?.let {
                val snackbarResult = snackbarHostState.showSnackbar(it, actionLabel = actionRetry)
                when (snackbarResult) {
                    SnackbarResult.ActionPerformed -> onRetry()
                    else -> {}
                }
            }
        }
    }

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = bottomSheetScaffoldState,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
                    }
                },
                actions = {
                    AnimatedContent(isInWatchlist) { added ->
                        when (added) {
                            null -> CircularProgressIndicator()
                            false -> IconButton(
                                onClick = viewModel::addToWatchlist,
                                enabled = !loadingWatchlist
                            ) {
                                Icon(
                                    painterResource(R.drawable.baseline_playlist_add_24),
                                    contentDescription = "add to watchlist"
                                )
                            }

                            true -> FilledTonalButton(
                                onClick = viewModel::removeFromWatchlist,
                                enabled = !loadingWatchlist
                            ) {
                                Icon(
                                    painterResource(R.drawable.baseline_playlist_add_check_24),
                                    contentDescription = "remove from watchlist"
                                )
                            }
                        }
                    }
                }
            )
        },
        sheetContent = {
            movieDetails?.let {
                MovieSheet(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    movie = it
                )
            }
            tvSerialDetails?.let {
                TvSerialSheet(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    tvSerial = it
                )
            }
        },
        sheetPeekHeight = 100.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .consumeWindowInsets(it),
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                UiState.Loading -> {
                    CircularProgressIndicator()
                }

                else -> {
                    movieDetails?.let { movie ->
                        AsyncImage(
                            modifier = Modifier.fillMaxSize(),
                            model = movie.posterUrl,
                            contentDescription = movie.title,
                            contentScale = ContentScale.FillWidth
                        )
                    }
                    tvSerialDetails?.let { tvSerial ->
                        AsyncImage(
                            modifier = Modifier.fillMaxSize(),
                            model = tvSerial.posterUrl,
                            contentDescription = tvSerial.title,
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }
            }

        }
    }
}