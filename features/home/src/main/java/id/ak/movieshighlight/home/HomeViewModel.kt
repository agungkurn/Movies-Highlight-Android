package id.ak.movieshighlight.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ak.movieshighlight.domain.usecase.GetMoviePagingSource
import id.ak.movieshighlight.domain.usecase.GetTvPagingSource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviePagingSource: GetMoviePagingSource,
    private val getTvPagingSource: GetTvPagingSource
) : ViewModel() {
    val movies = Pager(
        config = PagingConfig(getMoviePagingSource.pageSize),
        pagingSourceFactory = {
            getMoviePagingSource()
        }
    ).flow.cachedIn(viewModelScope)

    val tvSeries = Pager(
        config = PagingConfig(getTvPagingSource.pageSize),
        pagingSourceFactory = {
            getTvPagingSource()
        }
    ).flow.cachedIn(viewModelScope)
}