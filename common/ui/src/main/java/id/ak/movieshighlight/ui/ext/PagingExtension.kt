package id.ak.movieshighlight.ui.ext

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

fun LazyPagingItems<*>.isLoading() = loadState.refresh == LoadState.Loading
fun LazyPagingItems<*>.isEmpty() = loadState.refresh is LoadState.NotLoading && itemCount == 0