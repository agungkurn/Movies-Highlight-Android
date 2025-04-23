package id.ak.movieshighlight.home.tv

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import id.ak.movieshighlight.domain.entity.TvSerialListItem
import id.ak.movieshighlight.home.composable.FilmItem
import id.ak.movieshighlight.ui.composable.CustomLazyVerticalGrid
import id.ak.movieshighlight.ui.ext.isLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvSeriesList(
    tvSeries: LazyPagingItems<TvSerialListItem>,
    openDetails: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    PullToRefreshBox(isRefreshing = tvSeries.isLoading(), onRefresh = { tvSeries.refresh() }) {
        CustomLazyVerticalGrid(
            modifier = modifier,
            lazyPagingItems = tvSeries,
            columns = GridCells.Fixed(2)
        ) {
            items(tvSeries.itemCount) { i ->
                tvSeries[i]?.let {
                    FilmItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2f / 3f)
                            .clickable { openDetails(it.id) },
                        posterUrl = it.posterPath,
                        title = it.title
                    )
                }
            }
        }
    }
}