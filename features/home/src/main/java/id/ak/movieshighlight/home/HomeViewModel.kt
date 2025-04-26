package id.ak.movieshighlight.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ak.movieshighlight.domain.usecase.list.GetMoviePagingData
import id.ak.movieshighlight.domain.usecase.list.GetTvPagingData
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getMoviePagingData: GetMoviePagingData,
    getTvPagingData: GetTvPagingData
) : ViewModel() {
    val movies = getMoviePagingData().cachedIn(viewModelScope)

    val tvSeries = getTvPagingData().cachedIn(viewModelScope)
}