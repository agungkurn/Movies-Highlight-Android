package id.ak.movieshighlight.home.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import id.ak.movieshighlight.domain.entity.MovieListItem
import id.ak.movieshighlight.ui.composable.CustomLazyVerticalGrid
import id.ak.movieshighlight.ui.composable.FilmItem
import id.ak.movieshighlight.ui.ext.isLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieList(
    movies: LazyPagingItems<MovieListItem>,
    openDetails: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    PullToRefreshBox(isRefreshing = movies.isLoading(), onRefresh = { movies.refresh() }) {
        CustomLazyVerticalGrid(
            modifier = modifier,
            lazyPagingItems = movies,
            columns = GridCells.Fixed(2)
        ) {
            items(movies.itemCount) { i ->
                movies[i]?.let {
                    FilmItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2f / 3f)
                            .clickable(onClick = { openDetails(it.id) }),
                        posterUrl = it.posterPath,
                        title = it.title
                    )
                }
            }
        }
    }
}