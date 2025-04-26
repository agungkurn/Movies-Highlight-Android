package id.ak.movieshighlight.watchlist.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import id.ak.movieshighlight.domain.entity.Watchlist
import id.ak.movieshighlight.ui.composable.CustomLazyVerticalGrid
import id.ak.movieshighlight.ui.composable.FilmItem
import id.ak.movieshighlight.ui.ext.isLoading
import id.ak.movieshighlight.watchlist.list.EmptyState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieList(
    movies: LazyPagingItems<Watchlist>,
    openDetails: (Watchlist) -> Unit,
    modifier: Modifier = Modifier
) {
    PullToRefreshBox(isRefreshing = movies.isLoading(), onRefresh = { movies.refresh() }) {
        CustomLazyVerticalGrid(
            modifier = modifier,
            lazyPagingItems = movies,
            columns = GridCells.Fixed(2),
            emptyState = {
                EmptyState(type = Watchlist.Type.Movie, modifier = Modifier.fillMaxSize())
            }
        ) {
            items(movies.itemCount) { i ->
                movies[i]?.let {
                    FilmItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2f / 3f)
                            .clickable(onClick = { openDetails(it) }),
                        posterUrl = it.posterUrl,
                        title = it.title
                    )
                }
            }
        }
    }
}