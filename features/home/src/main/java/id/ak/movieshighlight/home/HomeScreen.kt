package id.ak.movieshighlight.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import id.ak.convention.features.home.R
import id.ak.movieshighlight.home.movies.MovieList
import id.ak.movieshighlight.home.tv.TvSeriesList
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    openMovieDetails: (Int) -> Unit,
    openTvSerialDetails: (Int) -> Unit,
    openWatchlist: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val movies = viewModel.movies.collectAsLazyPagingItems()
    val tvSeries = viewModel.tvSeries.collectAsLazyPagingItems()

    val topAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val coroutineScope = rememberCoroutineScope()

    val tabs = stringArrayResource(id.ak.convention.common.ui.R.array.list_tabs)
    val pagerState = rememberPagerState { tabs.size }

    Scaffold(
        modifier = modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                scrollBehavior = topAppBarScrollBehavior,
                title = { Text(stringResource(id.ak.convention.common.ui.R.string.app_name)) },
                actions = {
                    IconButton(onClick = openWatchlist) {
                        Icon(
                            painterResource(R.drawable.baseline_playlist_play_24),
                            contentDescription = "Watchlist"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .consumeWindowInsets(it)
        ) {
            PrimaryTabRow(selectedTabIndex = pagerState.currentPage) {
                tabs.forEachIndexed { i, title ->
                    Tab(
                        selected = i == pagerState.currentPage,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(i)
                            }
                        },
                        text = { Text(text = title) }
                    )
                }
            }
            HorizontalPager(state = pagerState) { i ->
                when (i) {
                    0 -> {
                        MovieList(
                            modifier = Modifier.fillMaxWidth(),
                            movies = movies,
                            openDetails = openMovieDetails
                        )
                    }

                    1 -> {
                        TvSeriesList(
                            modifier = Modifier.fillMaxWidth(),
                            tvSeries = tvSeries,
                            openDetails = openTvSerialDetails
                        )
                    }
                }
            }
        }
    }
}