package id.ak.movieshighlight.details

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import id.ak.convention.features.details.R
import id.ak.movieshighlight.details.sheet.MovieSheet
import id.ak.movieshighlight.details.sheet.TvSerialSheet
import id.ak.movieshighlight.ui.state.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    movieId: Int?,
    tvSerialId: Int?,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<DetailsViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val movieDetails by viewModel.movieDetails.collectAsStateWithLifecycle()
    val tvSerialDetails by viewModel.tvSerialDetails.collectAsStateWithLifecycle()
    val isInWatchlist by viewModel.isInWatchlist.collectAsStateWithLifecycle(null)
    val loadingWatchlist by viewModel.loadingWatchlist.collectAsStateWithLifecycle()

    val actionRetry = stringResource(id.ak.convention.common.ui.R.string.action_retry)

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        when {
            movieId != null -> viewModel.setMovieId(movieId)
            tvSerialId != null -> viewModel.setTvSerialId(tvSerialId)
        }
    }

    LaunchedEffect(uiState) {
        if (uiState is UiState.Error) {
            snackbarHostState.currentSnackbarData?.dismiss()
            (uiState as UiState.Error).message?.let {
                val snackbarResult = snackbarHostState.showSnackbar(it, actionLabel = actionRetry)
                when (snackbarResult) {
                    SnackbarResult.ActionPerformed -> {
                        when {
                            movieId != null -> viewModel.setMovieId(movieId)
                            tvSerialId != null -> viewModel.setTvSerialId(tvSerialId)
                        }
                    }

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

                            else -> {}
                        }
                    }
                }
            )
        },
        sheetContent = {
            Box(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .navigationBarsPadding()
            ) {
                movieDetails?.let {
                    MovieSheet(
                        modifier = Modifier.fillMaxWidth(),
                        movie = it
                    )
                }
                tvSerialDetails?.let {
                    TvSerialSheet(
                        modifier = Modifier.fillMaxWidth(),
                        tvSerial = it
                    )
                }
            }
        },
        sheetPeekHeight = 100.dp + WindowInsets.navigationBars.asPaddingValues()
            .calculateBottomPadding()
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