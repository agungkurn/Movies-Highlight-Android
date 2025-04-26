package id.ak.movieshighlight.watchlist.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import id.ak.convention.features.watchlist.R
import id.ak.movieshighlight.domain.entity.Watchlist
import id.ak.movieshighlight.watchlist.WatchlistViewModel
import id.ak.movieshighlight.watchlist.movies.MovieList
import id.ak.movieshighlight.watchlist.tv.TvSeriesList
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchlistScreen(
    viewModel: WatchlistViewModel,
    onNavigateUp: () -> Unit,
    openDetails: (Watchlist) -> Unit,
    modifier: Modifier = Modifier
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()
    val tvSeries = viewModel.tvSeries.collectAsLazyPagingItems()

    val tabs = stringArrayResource(id.ak.convention.common.ui.R.array.list_tabs)

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { tabs.size }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.watchlist_title))
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back"
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
                            openDetails = openDetails
                        )
                    }

                    1 -> {
                        TvSeriesList(
                            modifier = Modifier.fillMaxWidth(),
                            tvSeries = tvSeries,
                            openDetails = openDetails
                        )
                    }
                }
            }
        }
    }
}